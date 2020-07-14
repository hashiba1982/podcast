package com.jcl.youngsquare.config

import com.example.podcast.network.NetworkConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


import java.util.concurrent.TimeUnit

class RetrofitClient {

    fun create():ApiService{
        val retrofit = Retrofit.Builder()
                .baseUrl(NetworkConfig.DOMAIN)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(getClient())
                .build()

        return retrofit.create(ApiService::class.java)
    }

    private fun getClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(10, TimeUnit.SECONDS)
        builder.writeTimeout(10, TimeUnit.SECONDS)
        builder.readTimeout(10, TimeUnit.SECONDS)

        return builder.build()
    }
}