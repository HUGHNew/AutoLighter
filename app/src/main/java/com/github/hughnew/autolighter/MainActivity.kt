package com.github.hughnew.autolighter

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private val permissionLauncher =
        registerForActivityResult(WriteSettingContract()) {
            if (it) {
                setScreenBrightness(127)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        transStatusBar()
        findViewById<TextView>(R.id.text).apply {
            text = spannableString
        }
        if (!Settings.System.canWrite(this)) {
            permissionLauncher.launch(null)
        }
    }

    companion object {
        const val HINT = "AutoLighter: 两次快速亮屏息屏后 调整亮度为50%"
        const val TAG = "AutoLighter.MainActivity"
        val spannableString = SpannableString(HINT).apply {
            setSpan(
                StyleSpan(Typeface.BOLD),
                0,
                HINT.indexOf(' '),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
    }
}