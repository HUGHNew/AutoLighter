package com.github.hughnew.autolighter.helpers

import android.content.Context
import android.provider.Settings
import android.widget.Toast
import androidx.core.app.NotificationManagerCompat
import com.github.hughnew.autolighter.MainActivity

fun Context.isScreenAutoBrightness(): Boolean {
    return Settings.System.getInt(
        contentResolver,
        Settings.System.SCREEN_BRIGHTNESS_MODE
    ) == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC
}

fun Context.getScreenBrightness(): Int =
    Settings.System.getInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS)

fun Context.setScreenBrightness(bright: Int = MainActivity.HALF_BRIGHTNESS): Boolean {
    if (isScreenAutoBrightness() || !Settings.System.canWrite(this)) return false
    require(bright in 0..255)
    Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS, bright)
    return true
}

fun Context.isNotificationEnabled(): Boolean =
    NotificationManagerCompat.from(this).areNotificationsEnabled()

fun Context.makeToast(msg:String) =
    Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()