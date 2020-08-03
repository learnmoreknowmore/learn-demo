package com.example.pdf.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig


object ByItemDataRepository {
    val data = Pager(PagingConfig(10)) {
        ByItemDataSource()
    }.flow
}