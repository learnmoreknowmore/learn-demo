package com.example.pdf.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pdf.R
import com.example.pdf.bean.News

class ByPageAdapter : PagedListAdapter<News.StoriesBean, ByPageAdapter.ByItemViewHolder>(diffCallback) {


    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<News.StoriesBean>() {
            override fun areItemsTheSame(oldItem: News.StoriesBean, newItem:
            News.StoriesBean): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: News.StoriesBean, newItem:
            News.StoriesBean): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ByItemViewHolder {
        return ByItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.by_page_rec_item, parent, false))
    }

    override fun onBindViewHolder(holder: ByItemViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    class ByItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var imageView: ImageView
        private lateinit var textView: TextView

        fun bindTo(story: News.StoriesBean?) {
            imageView = itemView.findViewById(R.id.iv)
            textView = itemView.findViewById(R.id.tv)

            story?.let {
                Glide.with(imageView.context).load(it.images!![0]).into(imageView)
                textView.text = it.title
            }
        }
    }
}















