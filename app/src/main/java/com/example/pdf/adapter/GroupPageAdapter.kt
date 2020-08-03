package com.example.pdf.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pdf.R
import com.example.pdf.bean.GroupResult
import com.example.pdf.bean.News

class GroupPageAdapter : PagingDataAdapter<GroupResult.GroupBean, GroupPageAdapter.ByItemViewHolder>(diffCallback) {


    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<GroupResult.GroupBean>() {
            override fun areItemsTheSame(oldItem: GroupResult.GroupBean, newItem:
            GroupResult.GroupBean): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: GroupResult.GroupBean, newItem:
            GroupResult.GroupBean): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ByItemViewHolder {
        return ByItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.by_page_rec_item, parent, false))
    }

    override fun onBindViewHolder(holder: ByItemViewHolder, position: Int) {
        holder.bindTo(getItem(position)!!)
    }

    class ByItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var imageView: ImageView
        private lateinit var textView: TextView

        fun bindTo(story: GroupResult.GroupBean) {
            imageView = itemView.findViewById(R.id.iv)
            textView = itemView.findViewById(R.id.tv)

            story?.let {
                Glide.with(imageView.context).load(it.uheadimg).into(imageView)
                textView.text = it.content
            }
        }
    }
}















