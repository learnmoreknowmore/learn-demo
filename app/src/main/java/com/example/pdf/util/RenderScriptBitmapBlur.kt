package com.example.pdf.util

import android.content.Context
import android.graphics.Bitmap
import androidx.annotation.IntRange
import androidx.renderscript.Allocation
import androidx.renderscript.Element
import androidx.renderscript.RenderScript
import androidx.renderscript.ScriptIntrinsicBlur

class RenderScriptBitmapBlur(var context: Context) {
    private var renderScript: RenderScript? = null

    init {
        renderScript = RenderScript.create(context)
    }

    fun getBlurBitmap(@IntRange(from = 1, to = 25) radius: Int, original: Bitmap): Bitmap {
        // 使用Renderscript和in/out位图创建分配(in/out)
        var input: Allocation = Allocation.createFromBitmap(renderScript, original)

        var output: Allocation = Allocation.createTyped(renderScript, input.type)
        // 使用Renderscript创建一个固有的模糊脚本
        var scriptIntrinsicBlur: ScriptIntrinsicBlur = ScriptIntrinsicBlur.create(
            renderScript,
            Element.U8_4(renderScript)
        )
        //设置模糊半径0~25
        scriptIntrinsicBlur.setRadius(radius.toFloat())
        //执行渲染脚本
        scriptIntrinsicBlur.setInput(input)
        scriptIntrinsicBlur.forEach(output)
        // 将out分配创建的最终位图复制到original
        output.copyTo(original)
        return original
    }

}