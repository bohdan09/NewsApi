package com.example.lampa.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitImpl {
    companion object {
        private const val BASE_URL = "http://188.40.167.45:3001"

        private var instance: ApiService? = null

        fun retrofitInstance(): ApiService {
            if (instance == null) {
                instance = Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build()
                    .create(ApiService::class.java)
            }

            return instance!!
        }
    }
}