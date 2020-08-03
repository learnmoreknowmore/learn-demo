package com.example.pdf.api

import com.example.pdf.bean.News
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsService {
    @GET("before/{time}")
    fun getNews(@Path("time")time: Long): Observable<News>
}



