package com.example.pdf.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

class PersonViewModel(application: Application) : AndroidViewModel(application) {
    companion object {
        private const val PAGE_SIZE = 30
        private const val ENABLE_PLACEHOLDER = true
    }

    private val mPersonDao = PersonDatabase.get(application).personDao()

    val persons = LivePagedListBuilder(mPersonDao.getAllPersons(), PagedList
        .Config.Builder()
        .setPageSize(PAGE_SIZE)
        .setEnablePlaceholders(ENABLE_PLACEHOLDER).build()).build()
}












