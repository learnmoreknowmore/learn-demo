package com.example.pdf.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiGenerate {
    companion object{
        private fun retrofit():Retrofit = Retrofit.Builder()
            .baseUrl("https://news-at.zhihu.com/api/4/news/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        fun getNewsService(): NewsService = retrofit().create(NewsService::class.java)
        private fun retrofit1():Retrofit = Retrofit.Builder()
            .baseUrl("https://api.dev.1001if.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(getClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        private fun getClient(): OkHttpClient {
            val builder = OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor { chain: Interceptor.Chain ->
                    val request = chain.request()
                        .newBuilder()
                        .addHeader("Accept", "*/*")
                        .addHeader("Content-Type", "application/x-www-form-urlencoded")
                        .addHeader("Accept-Encoding", "gzip, deflate")
                        .addHeader("Connection", "keep-alive")
                        .addHeader("version", "1.0.67-android")
                        .build()
                    chain.proceed(request)
                }
            return builder.build()
        }

        fun getGroupService():GroupService = retrofit1().create(GroupService::class.java)
    }

}








