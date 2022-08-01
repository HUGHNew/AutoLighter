package com.github.hughnew.autolighter

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.provider.Settings
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.hughnew.autolighter.helpers.setScreenBrightness
import com.github.hughnew.autolighter.helpers.transStatusBar

class MainActivity : AppCompatActivity() {
    private val permissionLauncher =
        registerForActivityResult(WriteSettingContract()) {
            if (it) {
                setScreenBrightness()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpViews()
        setUpServices()
        requirePermissions()
    }

    private fun setUpViews() {
        transStatusBar()
        findViewById<TextView>(R.id.text).apply {
            text = spannableString
            setOnClickListener {
                stopService(Intent(this@MainActivity, AutoLighterService::class.java))
            }
        }
    }

    private fun setUpServices() {
        startService(Intent(this, AutoLighterService::class.java))
    }

    private fun requirePermissions() {
        if (!Settings.System.canWrite(this)) {
            permissionLauncher.launch(null)
        }
    }

    companion object {
        const val TAG = "AutoLighter.MainActivity"
        const val APP = "AutoLighter"
        const val HALF_BRIGHTNESS = 127
        private const val HINT = "AutoLighter: 两次快速亮屏息屏后 调整亮度为50%"
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