package com.example.pdf.datasource

import androidx.paging.DataSource
import com.example.pdf.bean.News

class ByPageDataSourceFactory : DataSource.Factory<Long, News.StoriesBean>() {
    override fun create(): DataSource<Long, News.StoriesBean> = ByPageDataSource()
}


