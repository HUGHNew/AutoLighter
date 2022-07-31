package com.github.hughnew.autolighter

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.util.Log
import android.widget.TextView
import android.widget.Toast

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

        setUpViews()
        registerBroadcasts()
        requirePermissions()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterBroadcasts()
    }
    private fun setUpViews(){
        transStatusBar()
        findViewById<TextView>(R.id.text).apply {
            text = spannableString
        }
    }
    private fun requirePermissions(){
        if (!Settings.System.canWrite(this)) {
            permissionLauncher.launch(null)
        }
    }
    private fun registerBroadcasts(){
        registerReceiver(screenReceiver, IntentFilter().apply {
            addAction(Intent.ACTION_SCREEN_ON)
            addAction(Intent.ACTION_SCREEN_OFF)
        })
    }
    private fun unregisterBroadcasts(){
        unregisterReceiver(screenReceiver)
    }
    companion object {
        const val HINT = "AutoLighter: 两次快速亮屏息屏后 调整亮度为50%"
        const val TAG = "AutoLighter.MainActivity"
        const val HALF_BRIGHTNESS = 127
        val screenReceiver = ScreenReceiver()
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