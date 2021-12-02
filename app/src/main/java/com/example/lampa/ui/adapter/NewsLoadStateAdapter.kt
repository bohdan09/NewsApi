package com.example.lampa.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lampa.R
import com.example.lampa.databinding.NewsLoadingStateBinding

class NewsLoadStateAdapter(
    val retry: () -> Unit
) : LoadStateAdapter<NewsLoadStateAdapter.ViewHolder>() {
    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = NewsLoadingStateBinding.bind(item)
        fun bind(loadState: LoadState) {
            with(binding) {
                if (loadState is LoadState.Error) isError.text = loadState.error.message

                isLoading.isVisible = loadState is LoadState.Loading
                isError.isVisible = loadState is LoadState.Error
                retry.isVisible = loadState is LoadState.Error

                retry.setOnClickListener {
                    retry()
                }
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.news_loading_state, parent, false)
        return ViewHolder(item = view)
    }
}