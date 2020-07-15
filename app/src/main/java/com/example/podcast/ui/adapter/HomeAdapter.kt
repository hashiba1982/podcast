package com.example.podcast.ui.adapter

import android.content.Context

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.podcast.network.response.Podcast


class HomeAdapter(context: Context, from:Int, val adapterClickListener: OnAdapterClickListener?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var adapterClick: OnAdapterClickListener? = null

    var mContext: Context
    var mDataSet: ArrayList<Podcast> = ArrayList()
    var pageFrom: Int? = 0

    interface OnAdapterClickListener{
        fun OnItemClick(view: View?, position: Int)
    }

    init {
        mContext = context
        pageFrom = from
    }

    fun swapDataSet(tempSet: ArrayList<Podcast>) {
        tempSet.let {
            mDataSet = it
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mDataSet.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        val manager = recyclerView.layoutManager
        if (manager == null){

        }else {
            if (manager is GridLayoutManager) {
                val glm: GridLayoutManager = manager
                glm.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return glm.spanCount
                    }
                }
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.diary_item, parent, false)
        return HotDiaryViewHolder(view)
    }

    class HotDiaryViewHolder(view:View) : RecyclerView.ViewHolder(view) {
        var tv_newDiaryTag:TextView = view?.findViewById(R.id.tv_newDiaryTag) as TextView
    }
}
