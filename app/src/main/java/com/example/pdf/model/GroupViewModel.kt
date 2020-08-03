package com.example.pdf.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.pdf.datasource.ByItemDataRepository

class GroupViewModel(application: Application) : AndroidViewModel(application) {
    private val repository:ByItemDataRepository by lazy {
        ByItemDataRepository
    }
    suspend fun getList() = repository.data
}