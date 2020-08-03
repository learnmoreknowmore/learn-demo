package com.example.pdf.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class BaseActivity : AppCompatActivity() {
    private var compositeDisposable: CompositeDisposable? = null

    fun addSubscription(dispose: Disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()
        }
        compositeDisposable?.add(dispose)
    }

    fun clear() {
        if (compositeDisposable != null) {
            compositeDisposable?.clear()
        }
    }

    fun removeSubscription(dispose: Disposable) {
        if (compositeDisposable != null) {
            compositeDisposable?.remove(dispose)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        clear()
    }
}