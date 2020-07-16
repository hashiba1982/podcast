package com.example.podcast.ui

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.podcast.R
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

class MusicPlayerFragment : Fragment() {

    private val musicListViewModel:MusicListViewModel by sharedViewModel()

    private var simpleExoplayer: SimpleExoPlayer? = null

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

    private fun initView(){
        tv_musicTitle.text = musicListViewModel.getSelectedMusic().title
    }

    private fun initExoPlayer(){
        simpleExoplayer = ExoPlayerFactory.newSimpleInstance(
            DefaultRenderersFactory(context),
            DefaultTrackSelector(AdaptiveTrackSelection.Factory(DefaultBandwidthMeter())),
            DefaultLoadControl()
        )

        simpleExoplayer?.seekToDefaultPosition()

        var mediaSource = buildMediaSource(Uri.parse(musicListViewModel.getSelectedMusic().contentUrl))
        var loopingSource = LoopingMediaSource(mediaSource)
        simpleExoplayer?.prepare(loopingSource)

        simpleExoplayer?.playWhenReady = true
        simpleExoplayer?.addListener(object : Player.EventListener{
            override fun onPlaybackParametersChanged(playbackParameters: PlaybackParameters?) {}

            override fun onSeekProcessed() {}

            override fun onTracksChanged(trackGroups: TrackGroupArray?, trackSelections: TrackSelectionArray?) {}

            override fun onPlayerError(error: ExoPlaybackException?) {
                //mVideoListener?.OnVideoError()
            }

            override fun onLoadingChanged(isLoading: Boolean) {}

            override fun onPositionDiscontinuity(reason: Int) {}

            override fun onRepeatModeChanged(repeatMode: Int) {}

            override fun onShuffleModeEnabledChanged(shuffleModeEnabled: Boolean) {}

            override fun onTimelineChanged(timeline: Timeline?, manifest: Any?, reason: Int) {}

            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                when(playbackState) {
                    Player.STATE_BUFFERING -> {
                        //progress_image?.visibility = View.VISIBLE
                    }
                    Player.STATE_READY -> {
                        //progress_image?.visibility = View.GONE
                        //mVideoListener?.OnVideoReady()
                    }
                    Player.STATE_ENDED -> {
                        //mVideoListener?.OnVideoFinish()
                    }
                }
            }
        })
    }

    private fun buildMediaSource(uri: Uri) : MediaSource {
        val dataSourceFactory = DefaultDataSourceFactory(requireContext(), Util.getUserAgent(requireContext(), "ExoplayerSample"), DefaultBandwidthMeter())
        return ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(uri)
    }

    override fun onPause() {
        super.onPause()
        simpleExoplayer?.release()
    }
}