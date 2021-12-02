package com.example.lampa.data.repository

import androidx.paging.PagingSource
import com.example.lampa.data.data.source.NewsPagingSource
import com.example.lampa.domain.model.NewsItem

class NewsRepository(
    private val newsPagingSource: NewsPagingSource
) {
    fun getPagingSource(): PagingSource<Int, NewsItem> {
        return newsPagingSource
    }
}