package com.github.hughnew.autolighter

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.github.hughnew.autolighter.helpers.getScreenBrightness
import com.github.hughnew.autolighter.helpers.setScreenBrightness

class ScreenReceiver:BroadcastReceiver() {
    companion object{
        const val TAG = "AutoLighter.ScreenReceiver"
        const val SECOND = 1000L
        var lastTime : Long = unixMillis()
        var status: Boolean = false
        fun unixMillis() : Long = System.currentTimeMillis()
    }
    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            Intent.ACTION_SCREEN_ON -> {
                when (status) {
                    false -> {
                        lastTime = unixMillis()
                        status = true
                    }
                    true -> {
                        if (unixMillis() - lastTime < SECOND) {
                            Log.w(TAG,"result:${context.setScreenBrightness()}")
                        }
                        lastTime = unixMillis()
                        status = false
                    }
                }
                Log.d(TAG,"lastTime:$lastTime, status:$status")
                Log.w(TAG,"current brightness:${context.getScreenBrightness()}")
            }
        }
    }
}