package com.github.hughnew.autolighter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContract

class EnableNotificationContract:ActivityResultContract<Unit,Boolean>() {
    override fun createIntent(context: Context, input: Unit?): Intent =
        Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
            putExtra(Settings.EXTRA_APP_PACKAGE,context.packageName)
            putExtra(Settings.EXTRA_CHANNEL_ID,context.applicationInfo.uid)
        } // refer : https://www.jianshu.com/p/1e27efb1dcac

    override fun parseResult(resultCode: Int, intent: Intent?): Boolean =
        resultCode == Activity.RESULT_OK
}