package com.github.hughnew.autolighter.helpers

import android.content.ContextWrapper
import android.content.Intent
import android.content.IntentFilter
import com.github.hughnew.autolighter.ScreenReceiver

val screenReceiver = ScreenReceiver()
fun ContextWrapper.registerBroadcasts(){
    val filter = IntentFilter().apply {
        addAction(Intent.ACTION_SCREEN_ON)
        addAction(Intent.ACTION_SCREEN_OFF)
    }
    registerReceiver(screenReceiver, filter)
}

fun ContextWrapper.unregisterBroadcasts(){
    unregisterReceiver(screenReceiver)
}