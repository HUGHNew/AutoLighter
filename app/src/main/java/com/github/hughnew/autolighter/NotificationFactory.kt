package com.github.hughnew.autolighter

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat

object NotificationFactory {
    fun build(context: Context, channelId: String, intent: PendingIntent? = null): Notification =
        NotificationCompat.Builder(context, channelId)
            .setContentTitle(MainActivity.APP)
            .setContentText("Tap to turn off this service")
            .setSmallIcon(R.drawable.ic_highlight_24)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    context.resources,
                    R.drawable.ic_highlight_48
                )
            )
            .setContentIntent(intent)
            .setAutoCancel(true)
            .build()
}