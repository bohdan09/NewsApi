package com.example.lampa.data.api

import com.example.lampa.domain.model.NewsItem
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/")
    suspend fun getNews(
        @Query("page") page: Int = 0
    ): List<NewsItem>
}