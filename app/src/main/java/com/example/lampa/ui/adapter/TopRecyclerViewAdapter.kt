package com.example.lampa.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lampa.R
import com.example.lampa.databinding.TopItemRecyclerBinding
import com.example.lampa.domain.model.NewsItem

class TopRecyclerViewAdapter : PagingDataAdapter<NewsItem, RecyclerView.ViewHolder>(MyDiffUtill) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MyViewHolder).bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.top_item_recycler, parent, false)
        return MyViewHolder(view)
    }

    inner class MyViewHolder(iten: View) : RecyclerView.ViewHolder(iten) {
        private val binding = TopItemRecyclerBinding.bind(iten)

        @SuppressLint("SetTextI18n")
        fun bind(newsItem: NewsItem) {
            with(binding) {
                topTitleNews.text = newsItem.title
                Glide.with(topImageNews).load(newsItem.img).centerCrop()
                    .placeholder(R.drawable.scrim).into(topImageNews)
                topDateNews.text = " - ${newsItem.time}"
                topSourceNews.text = newsItem.click_url
            }
        }
    }
}

private object MyDiffUtill : DiffUtil.ItemCallback<NewsItem>() {
    override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
        return oldItem == newItem
    }

}