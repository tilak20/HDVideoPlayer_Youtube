package com.downloadersocial.fastvideodownloader.ApiClient

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class YoutubeClient {

    companion object {

        fun getRetrofit(): Retrofit {

            return Retrofit.Builder().baseUrl("https://www.youtube.com/")
                .client(OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create()).build()
        }

        fun getVideoRetrofit(): Retrofit {
            return Retrofit.Builder().baseUrl("https://youtubei.googleapis.com/")
                .client(OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create()).build()
        }

        fun getSuggationRetrofit(): Retrofit {
            return Retrofit.Builder().baseUrl("https://suggestqueries-clients6.youtube.com/")
                .client(OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create()).build()
        }
    }
}