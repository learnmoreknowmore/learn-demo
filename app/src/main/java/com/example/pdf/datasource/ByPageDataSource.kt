package com.example.pdf.datasource

import android.annotation.SuppressLint
import androidx.paging.PageKeyedDataSource
import com.example.pdf.api.ApiGenerate
import com.example.pdf.api.NewsService
import com.example.pdf.bean.News
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*

class ByPageDataSource : PageKeyedDataSource<Long, News.StoriesBean>() {

    private lateinit var mNewsService: NewsService
    private val mDate = Calendar.getInstance().apply {
        add(Calendar.DATE, 1)
    }

    @SuppressLint("CheckResult")
    override fun loadInitial(
        params: LoadInitialParams<Long>, callback:
        LoadInitialCallback<Long, News.StoriesBean>
    ) {
        mNewsService = ApiGenerate.getNewsService()
        mNewsService.getNews(
            SimpleDateFormat(
                "yyyyMMdd",
                Locale.CHINA
            ).format(mDate.time).toLong()
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .subscribe {
                callback.onResult(it.stories, 0, it.date.toLong())
            }
    }

    override fun loadAfter(
        params: LoadParams<Long>, callback: LoadCallback<Long,
                News.StoriesBean>
    ) {
        mNewsService.getNews(params.key)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .subscribe {
                callback.onResult(it.stories, it.date.toLong())
            }
    }

    override fun loadBefore(
        params: LoadParams<Long>, callback: LoadCallback<Long,
                News.StoriesBean>
    ) {
        //这里不需要向上加载，因此无须实现
    }
}

