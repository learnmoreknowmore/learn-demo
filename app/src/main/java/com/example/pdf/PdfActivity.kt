package com.example.pdf

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.blankj.utilcode.util.ToastUtils
import com.example.pdf.base.BaseActivity
import com.example.pdf.util.PDFWorker
import com.tbruyelle.rxpermissions3.RxPermissions
import io.reactivex.rxjava3.functions.Consumer
import kotlinx.android.synthetic.main.activity_pdf.*

class PdfActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf)
        btn_generate.setOnClickListener {
            var dispose = RxPermissions(this)
                .request(android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe({ isGranted->
                    run {
                        if (isGranted) {
                            val worker = OneTimeWorkRequestBuilder<PDFWorker>().build()
                            WorkManager.getInstance(this).enqueue(worker)
                            ToastUtils.showShort("生成pdf成功！")
                        } else {
                            ToastUtils.showShort("请开启存储权限")
                        }
                    }
                }, { t-> ToastUtils.showShort(t.message) })
            addSubscription(dispose = dispose!!)

        }
    }

}