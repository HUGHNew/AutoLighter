package com.github.hughnew.autolighter

import android.app.Activity
import android.content.res.Configuration
import android.os.Build
import android.provider.Settings.System
import android.view.View
import android.view.WindowManager
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat

fun Activity.isScreenAutoBrightness(): Boolean {
    return System.getInt(
        contentResolver,
        System.SCREEN_BRIGHTNESS_MODE
    ) == System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC
}

fun Activity.setScreenBrightness(bright: Int): Boolean {
    if (isScreenAutoBrightness() || !System.canWrite(this)) return false
    require(bright in 0..255)
    System.putInt(contentResolver, System.SCREEN_BRIGHTNESS, bright)
    return true
}

fun Activity.transStatusBar() {
    if (Build.VERSION.SDK_INT in 21..29) {
        if (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
            == Configuration.UI_MODE_NIGHT_NO
        ) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    } else if (Build.VERSION.SDK_INT >= 30) {
        if (!resources.configuration.isNightModeActive) {
            WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars =
                true
            WindowCompat.setDecorFitsSystemWindows(window, false) // 高度调整
        }
    }
}