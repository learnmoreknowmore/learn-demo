package com.example.pdf.util

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.pdf.PdfDocument
import android.os.Environment
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.RequiresPermission
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.app.ActivityCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.blankj.utilcode.util.EncryptUtils
import com.blankj.utilcode.util.SizeUtils
import com.example.pdf.R
import java.io.File
import java.io.FileOutputStream

class PDFWorker(var context: Context, parameter: WorkerParameters) :
    CoroutineWorker(context, parameter) {
    override suspend fun doWork(): Result {
        try {
            var list: MutableList<String> = mutableListOf()
            //for (i in 0..2){
            list.add("北国风光，千里冰封，万里雪飘。")
            //}
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {

                return Result.failure()
            }
            buildPdf(context = context, list = list)
            return Result.success()
        } catch (ex: Exception) {
            return Result.failure()
        }
    }

    @RequiresPermission(allOf = [Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE])
    fun buildPdf(context: Context, list: MutableList<String>) {
        var view = LinearLayout(context)
        createViewContent(context, view, list)
        var pdfdoc = PdfDocument()
        var pdfInfo: PdfDocument.PageInfo = PdfDocument.PageInfo.Builder(100, 500, 1).create()
        //pdf页面
        var page: PdfDocument.Page = pdfdoc.startPage(pdfInfo)
        //往pdf上面添加内容
        var content: View = view
        content.draw(page.canvas)
        //完成pdf
        pdfdoc.finishPage(page)
        //写入内容
//            var path = Environment.getExternalStorageDirectory().absolutePath + File.separator+ context.resources.getString(
//                R.string.app_name) + System.currentTimeMillis()+".pdf"
//            var file = File(path)
        val pack = File(
            Environment.getExternalStorageDirectory().absolutePath,
            "yudaoshu"
        )
        if (!pack.exists()) {
            pack.mkdir()
        }
        var name = EncryptUtils.encryptMD5ToString(System.currentTimeMillis().toString())
        var file = File(pack, "$name.pdf")
        if (!file.exists()) {
            file.createNewFile()
        }
        pdfdoc.writeTo(FileOutputStream(file))

        //关闭资源
        pdfdoc.close()

    }

    private fun createViewContent(context: Context, view: LinearLayout, list: List<String>) {
        for (item in list) {
            var textView: AppCompatTextView = AppCompatTextView(context)
            textView.text = item
            textView.textSize = SizeUtils.sp2px(16f).toFloat()
            view.addView(textView)
        }
    }
}