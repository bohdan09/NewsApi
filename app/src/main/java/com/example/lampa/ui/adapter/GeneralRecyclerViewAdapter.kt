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
import com.example.lampa.databinding.PhotoNewsItemBinding
import com.example.lampa.databinding.TextNewsItemBinding
import com.example.lampa.domain.model.NewsItem

class RecyclerViewAdapter :
    PagingDataAdapter<NewsItem, RecyclerView.ViewHolder>(MyDiffUtil) {

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position)!!.img == null) TEXT_NEWS else PHOTO_TEXT_NEWS
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TEXT_NEWS -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.text_news_item, parent, false)
                TextNewsViewHolder(view)
            }

            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.photo_news_item, parent, false)
                PhotoNewsViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TEXT_NEWS -> (holder as TextNewsViewHolder).bind(getItem(position)!!)
            PHOTO_TEXT_NEWS -> (holder as PhotoNewsViewHolder).bind(getItem(position)!!)
        }
    }

    inner class TextNewsViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = TextNewsItemBinding.bind(item)

        @SuppressLint("SetTextI18n")
        fun bind(news: NewsItem) {
            with(binding) {
                newsTitle.text = news.title
                source.text = news.click_url
                date.text = " - ${news.time}"
            }
        }
    }


    inner class PhotoNewsViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = PhotoNewsItemBinding.bind(item)

        @SuppressLint("SetTextI18n")
        fun bind(news: NewsItem) {
            with(binding) {
                Glide.with(newsImage).load(news.img).centerCrop().into(newsImage)
                newsTitleTV.text = news.title
                sourceTV.text = news.click_url
                dateTV.text = " - ${news.time}"
            }
        }
    }

    companion object {
        const val TEXT_NEWS = 0
        const val PHOTO_TEXT_NEWS = 1
    }
}

private object MyDiffUtil : DiffUtil.ItemCallback<NewsItem>() {
    override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
        return oldItem == newItem
    }

}
