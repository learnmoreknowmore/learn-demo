package com.example.pdf

import android.app.Application
import com.blankj.utilcode.util.Utils

class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
    }
}