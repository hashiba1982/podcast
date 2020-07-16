package com.example.podcast.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.podcast.R
import com.example.podcast.tools.loadUrl
import com.example.podcast.ui.adapter.MusicListAdapter
import com.example.podcast.vm.MusicListViewModel
import kotlinx.android.synthetic.main.fragment_music_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MusicListFragment : Fragment() {

    private val musicListViewModel:MusicListViewModel by viewModel()

    private var mAdapter:MusicListAdapter? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_music_list, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        setObserver()

        musicListViewModel.getCastsDetailAPI()
    }

    private fun initView(){
        mAdapter = MusicListAdapter(requireContext(), object : MusicListAdapter.OnAdapterClickListener{
            override fun OnItemClick(view: View?, id: String) {
 /*               val bundle = Bundle()
                bundle.putString("cast_id", id)
                Navigation.findNavController(view!!).navigate(R.id.action_navigation_home_to_musiclist, bundle)*/
            }
        })
        rv_musicList.layoutManager = LinearLayoutManager(context)
        rv_musicList.adapter = mAdapter

    }

    private fun setObserver(){
        musicListViewModel.collection.observe(viewLifecycleOwner, Observer {
            iv_castImage.loadUrl(it.artworkUrl100)
            tv_castTitle.text = it.collectionName
            tv_subTitle.text = it.country + " " + it.artistName
            textView3.visibility = View.VISIBLE

            mAdapter?.swapDataSet(it.contentFeed.toCollection(ArrayList()))
        })
    }
}