package com.example.podcast.ui.adapter

import android.content.Context

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.podcast.R
import com.example.podcast.network.response.ContentFeed
import com.example.podcast.network.response.Podcast
import com.example.podcast.tools.loadUrl


class MusicListAdapter(context: Context, val adapterClickListener: OnAdapterClickListener?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var adapterClick: OnAdapterClickListener? = null

    var mContext: Context = context
    var mDataSet: ArrayList<ContentFeed> = ArrayList()

    interface OnAdapterClickListener{
        fun OnItemClick(view: View?, position: Int)
    }

    fun swapDataSet(tempSet: ArrayList<ContentFeed>) {
        tempSet.let {
            mDataSet = it
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mDataSet.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as MusicViewHolder

        if (adapterClickListener != null){
            adapterClick = adapterClickListener

            holder.itemView.setOnClickListener {
                adapterClick?.OnItemClick(it, position)
            }
        }

        holder.tv_musicTitle.text = mDataSet[position].title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.music_list_item, parent, false)
        return MusicViewHolder(view)
    }

    class MusicViewHolder(view:View) : RecyclerView.ViewHolder(view) {
        var tv_musicTitle:TextView = view.findViewById(R.id.tv_musicTitle) as TextView
    }
}
