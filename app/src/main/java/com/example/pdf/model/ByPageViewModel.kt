package com.example.pdf.model

import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.pdf.datasource.ByPageDataSourceFactory

class ByPageViewModel : ViewModel() {
    val stories = LivePagedListBuilder(
        ByPageDataSourceFactory(),
        PagedList.Config.Builder()
            .setPageSize(30)
            .setEnablePlaceholders(false).build()).build()
}





