package com.example.podcast.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.podcast.R
import com.example.podcast.ui.adapter.HomeAdapter
import com.example.podcast.vm.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel:HomeViewModel by viewModel()

    private var mAdapter:HomeAdapter? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        setObserver()

        homeViewModel.getCastsAPI()
    }

    private fun initView(){
        var gridLayoutManager = GridLayoutManager(context, 2)
        mAdapter = HomeAdapter(requireContext(), object :HomeAdapter.OnAdapterClickListener{
            override fun OnItemClick(view: View?, id: String) {
                val bundle = Bundle()
                bundle.putString("cast_id", id)
                Navigation.findNavController(view!!).navigate(R.id.action_navigation_home_to_musiclist, bundle)
            }
        })
        rv_casts.layoutManager = gridLayoutManager
        rv_casts.adapter = mAdapter

    }

    private fun setObserver(){
        homeViewModel.castDataSet.observe(viewLifecycleOwner, Observer {
            mAdapter?.swapDataSet(it.toCollection(ArrayList()))
        })
    }
}