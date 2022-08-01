package com.github.hughnew.autolighter.helpers

import android.content.ContextWrapper
import android.content.Intent
import android.content.IntentFilter
import com.github.hughnew.autolighter.ScreenReceiver

val screenReceiver = ScreenReceiver()
fun ContextWrapper.registerBroadcasts(){
    registerReceiver(screenReceiver, IntentFilter(Intent.ACTION_SCREEN_ON))
}

fun ContextWrapper.unregisterBroadcasts(){
    unregisterReceiver(screenReceiver)
}