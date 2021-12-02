package com.example.lampa.data.data.source

import androidx.paging.PagingSource
import com.example.lampa.data.api.ApiService
import com.example.lampa.domain.model.NewsItem

class NewsPagingSource(
    private val apiService: ApiService,
) : PagingSource<Int, NewsItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsItem> {
        return try {
            val nextPageNumber = params.key ?: 0
            val response = apiService.getNews(nextPageNumber)

            LoadResult.Page(
                data = response,
                prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
                nextKey = if (nextPageNumber < 4) nextPageNumber + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}