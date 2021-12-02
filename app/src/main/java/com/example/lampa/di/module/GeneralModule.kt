package com.example.lampa.di.module

import androidx.paging.PagingSource
import com.example.lampa.data.api.ApiService
import com.example.lampa.data.api.RetrofitImpl
import com.example.lampa.data.data.source.NewsPagingSource
import com.example.lampa.data.repository.NewsRepository
import com.example.lampa.domain.model.NewsItem
import com.example.lampa.domain.usecase.GetPagingSourceUseCase
import com.example.lampa.ui.adapter.RecyclerViewAdapter
import com.example.lampa.ui.adapter.TopRecyclerViewAdapter
import dagger.Module
import dagger.Provides

@Module
class GeneralModule {
    @Provides
    fun provideApiService(): ApiService {
        return RetrofitImpl.retrofitInstance()
    }

    @Provides
    fun provideGetNewsUseCase(
        newsRepository: NewsRepository
    ): GetPagingSourceUseCase {
        return GetPagingSourceUseCase(newsRepository = newsRepository)
    }

    @Provides
    fun providePagingSource(
        apiService: ApiService
    ): NewsPagingSource {
        return NewsPagingSource(apiService)
    }

    @Provides
    fun provideNewsRepository(
        newsPagingSource: NewsPagingSource
    ): NewsRepository {
        return NewsRepository(newsPagingSource = newsPagingSource)
    }

    @Provides
    fun provideNewsPagingSource(
        apiService: ApiService
    ): PagingSource<Int, NewsItem> {
        return NewsPagingSource(apiService = apiService)
    }

    @Provides
    fun provideGeneralRecyclerViewAdapter(): RecyclerViewAdapter {
        return RecyclerViewAdapter()
    }

    @Provides
    fun provideTopRecyclerViewAdapter(): TopRecyclerViewAdapter {
        return TopRecyclerViewAdapter()
    }
}