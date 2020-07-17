package com.example.podcast.ui.adapter

import android.content.Context

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.podcast.R
import com.example.podcast.network.response.Podcast
import com.example.podcast.tools.loadUrl


class HomeAdapter(context: Context, val adapterClickListener: OnAdapterClickListener?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var adapterClick: OnAdapterClickListener? = null

    var mContext: Context = context
    var mDataSet: ArrayList<Podcast> = ArrayList()

    interface OnAdapterClickListener{
        fun OnItemClick(view: View?, id: String)
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

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as CastViewHolder

        if (adapterClickListener != null){
            adapterClick = adapterClickListener

            holder.itemView.setOnClickListener {
                adapterClick?.OnItemClick(it, mDataSet[position].id)
            }
        }

        holder.tv_castTitle.text = mDataSet[position].artistName
        holder.tv_castSubTitle.text = mDataSet[position].name
        holder.iv_castImage.loadUrl(mDataSet[position].artworkUrl100)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.cast_item, parent, false)
        return CastViewHolder(view)
    }

    class CastViewHolder(view:View) : RecyclerView.ViewHolder(view) {
        var tv_castTitle:TextView = view.findViewById(R.id.tv_castTitle) as TextView
        var tv_castSubTitle:TextView = view.findViewById(R.id.tv_castSubTitle) as TextView
        var iv_castImage:ImageView = view.findViewById(R.id.iv_castImage) as ImageView
    }
}
