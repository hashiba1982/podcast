package com.example.podcast.ui

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import com.example.podcast.R
import com.example.podcast.tools.loadUrl
import com.example.podcast.vm.MusicListViewModel
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.LoopingMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.fragment_music_player.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.util.*

class MusicPlayerFragment : Fragment() {

    private val musicListViewModel: MusicListViewModel by sharedViewModel()

    private var simpleExoplayer: SimpleExoPlayer? = null
    private var isPlaying: Boolean = false
    private var handler: Handler = Handler()
    private var runnable: Runnable? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_music_player, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initExoPlayer()
    }

    private fun initView() {
        tv_musicTitle.text = musicListViewModel.getSelectedMusic().title
        iv_castBigImage.loadUrl(musicListViewModel.collection.value!!.artworkUrl600)

        iv_play.setOnClickListener {
            playMusic()
        }

        iv_forword.setOnClickListener {
            if (isPlaying) {
                simpleExoplayer?.seekTo(simpleExoplayer!!.contentPosition + 30000)
            }
        }

        iv_back.setOnClickListener {
            if (isPlaying) {
                var pos = if (simpleExoplayer!!.contentPosition - 30000 < 0)
                    0
                else
                    simpleExoplayer!!.contentPosition - 30000
                simpleExoplayer?.seekTo(pos)
            }
        }
    }

    private fun playMusic(){

        if (isPlaying) {
            simpleExoplayer?.playWhenReady = false
            iv_play.setImageResource(R.drawable.exo_controls_play)
        } else {
            simpleExoplayer?.playWhenReady = true
            iv_play.setImageResource(R.drawable.exo_controls_pause)
        }

        simpleExoplayer?.playbackState
    }

    private fun initExoPlayer() {
        simpleExoplayer = ExoPlayerFactory.newSimpleInstance(
            DefaultRenderersFactory(context),
            DefaultTrackSelector(AdaptiveTrackSelection.Factory(DefaultBandwidthMeter())),
            DefaultLoadControl()
        )

        simpleExoplayer?.seekToDefaultPosition()

        var mediaSource =
            buildMediaSource(Uri.parse(musicListViewModel.getSelectedMusic().contentUrl))
        var loopingSource = LoopingMediaSource(mediaSource)
        simpleExoplayer?.prepare(loopingSource)

        simpleExoplayer?.playWhenReady = true
        simpleExoplayer?.addListener(object : Player.EventListener {
            override fun onPlaybackParametersChanged(playbackParameters: PlaybackParameters?) {}

            override fun onSeekProcessed() {}

            override fun onTracksChanged(
                trackGroups: TrackGroupArray?,
                trackSelections: TrackSelectionArray?
            ) {
            }

            override fun onPlayerError(error: ExoPlaybackException?) {
                //mVideoListener?.OnVideoError()
            }

            override fun onLoadingChanged(isLoading: Boolean) {}

            override fun onPositionDiscontinuity(reason: Int) {}

            override fun onRepeatModeChanged(repeatMode: Int) {}

            override fun onShuffleModeEnabledChanged(shuffleModeEnabled: Boolean) {}

            override fun onTimelineChanged(timeline: Timeline?, manifest: Any?, reason: Int) {}

            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                when (playbackState) {
                    Player.STATE_BUFFERING -> {
                        //progress_image?.visibility = View.VISIBLE
                    }
                    Player.STATE_READY -> {
                        //progress_image?.visibility = View.GONE
                        //mVideoListener?.OnVideoReady()
                        isPlaying = !isPlaying
                        iv_play.setImageResource(R.drawable.exo_controls_pause)
                        setMusicTime()

                    }
                    Player.STATE_ENDED -> {
                        //mVideoListener?.OnVideoFinish()
                    }
                }
            }
        })

        initSeekBar()
    }

    private fun initSeekBar() {
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (!fromUser) return
                if (progress == 0 || seekBar?.max == 0) return
                var pos = progress * 1000
                simpleExoplayer?.seekTo(pos.toLong())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
    }

    private fun makeTimeString(secs: Int): String? {

        var sFormatBuilder = StringBuilder()
        var sFormatter = Formatter(sFormatBuilder, Locale.getDefault())

        val durationformat = getString(R.string.conver_time)

        var sec = secs / 3600 // 秒
        var minute = secs % 3600 / 60 // 分
        var hour = secs % 3600 % 60 % 60 // 時

        return sFormatter.format(durationformat, sec, minute, hour).toString().trim()
    }

    private fun setMusicTime() {

        runnable = object : Runnable {
            override fun run() {
                if (simpleExoplayer != null) {
                    val durationSecond = (simpleExoplayer!!.duration / 1000).toInt()
                    val currentSecond = (simpleExoplayer!!.currentPosition / 1000).toInt()

                    tv_timeEnd.text = makeTimeString(durationSecond)
                    tv_timeStart.text = makeTimeString(currentSecond)
                    seekBar.max = durationSecond
                    seekBar.progress = currentSecond
                    handler.postDelayed(this, 1000)
                }
            }
        }
        handler.post(runnable)
    }


    private fun buildMediaSource(uri: Uri): MediaSource {
        val dataSourceFactory = DefaultDataSourceFactory(
            requireContext(),
            Util.getUserAgent(requireContext(), "ExoplayerSample"),
            DefaultBandwidthMeter()
        )
        return ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(uri)
    }

    override fun onResume() {
        super.onResume()

        if (simpleExoplayer != null) {
            simpleExoplayer?.playWhenReady = true
        }
    }

    override fun onPause() {
        super.onPause()

        handler.removeCallbacks(runnable)

        if (simpleExoplayer != null) {
            simpleExoplayer?.playWhenReady = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        handler.removeCallbacks(runnable)

        if (simpleExoplayer != null) {
            simpleExoplayer?.release()
            simpleExoplayer = null
        }
    }
}