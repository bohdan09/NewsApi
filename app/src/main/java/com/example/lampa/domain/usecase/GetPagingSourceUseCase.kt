package com.example.lampa.domain.usecase

import androidx.paging.PagingSource
import com.example.lampa.data.repository.NewsRepository
import com.example.lampa.domain.model.NewsItem

class GetPagingSourceUseCase(
    private val newsRepository: NewsRepository
) {
    fun execute(): PagingSource<Int, NewsItem> {
        return newsRepository.getPagingSource()
    }
}