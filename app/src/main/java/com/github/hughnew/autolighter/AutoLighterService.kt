package com.github.hughnew.autolighter

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import com.github.hughnew.autolighter.helpers.unregisterBroadcasts
import com.github.hughnew.autolighter.helpers.registerBroadcasts

class AutoLighterService : Service() {

    override fun onCreate() {
        super.onCreate()
        Log.w(MainActivity.TAG, "Create Service")
        registerBroadcasts()
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "lighter"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId, "AutoLighter",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            manager.createNotificationChannel(channel)
        }
        startForeground(
            1,
            NotificationFactory.build(
                this, channelId,
                PendingIntent.getActivity(
                    this, 0,
                    Intent(this, MainActivity::class.java),
                    PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
            )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.w(MainActivity.TAG, "Destroy Service")
        unregisterBroadcasts()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.w(MainActivity.TAG, "Start Command")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder? {
        // Return the communication channel to the service.
        return null
    }
}