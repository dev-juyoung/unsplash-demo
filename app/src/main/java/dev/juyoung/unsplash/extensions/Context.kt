package dev.juyoung.unsplash.extensions

import android.content.Context
import android.content.pm.ApplicationInfo
import android.util.DisplayMetrics
import android.view.WindowManager

val Context.isDebuggable: Boolean
    get() = 0 != applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE

val Context.deviceWidth: Int
    get() {
        val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val displayMetrics = DisplayMetrics().apply {
            windowManager.defaultDisplay.getRealMetrics(this)
        }

        return displayMetrics.widthPixels
    }
