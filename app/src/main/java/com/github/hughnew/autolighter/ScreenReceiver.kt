package com.github.hughnew.autolighter

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class ScreenReceiver:BroadcastReceiver() {
    companion object{
        const val SECOND = 1000L
        var lastTime : Long = 0
        var status: Boolean = false
        fun unixMillis() : Long = System.currentTimeMillis()
    }
    override fun onReceive(context: Context, intent: Intent) {
        Log.d(MainActivity.TAG,"Broadcast: ${intent.action}")
        when (intent.action) {
            Intent.ACTION_SCREEN_ON -> {
                when (status) {
                    false -> {
                        lastTime = unixMillis()
                        status = true
                    }
                    true -> {
                        if (unixMillis() - lastTime < SECOND) {
                            (context as Activity).setScreenBrightness(MainActivity.HALF_BRIGHTNESS)
                            status = false
                        }
                    }
                }
            }
//            Intent.ACTION_SCREEN_OFF -> {}
        }
    }
}