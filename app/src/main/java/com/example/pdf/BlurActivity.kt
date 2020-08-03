package com.example.pdf

import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.example.pdf.util.RenderScriptBitmapBlur
import jp.wasabeef.blurry.Blurry
import kotlinx.android.synthetic.main.activity_blur.*

class BlurActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blur)
        seekBar.max = 25
        blurView(iv1)
        blurView(iv2)
        blurView(iv3)
        blurView(iv4)
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                var radius = seekBar.progress
                if (radius < 1) {
                    radius = 1
                }
                var bitmap = BitmapFactory.decodeResource(resources, R.mipmap.image)
                blur.setImageBitmap(
                    RenderScriptBitmapBlur(this@BlurActivity).getBlurBitmap(
                        radius,
                        bitmap
                    )
                )

            }
        })

    }

    fun blurGroup(radius: Int, viewGroup: ViewGroup) {
        Blurry.with(this)
            .radius(radius)
            .sampling(2)
            .async()
            .animate(500)
            .onto(viewGroup)
    }

    private fun blurView(view: ImageView) {
        Blurry.with(this)
            .radius(25)
            .sampling(1)
            .color(Color.argb(66, 0, 255, 255))
            .async()
            .capture(view)
            .into(view)
    }
}