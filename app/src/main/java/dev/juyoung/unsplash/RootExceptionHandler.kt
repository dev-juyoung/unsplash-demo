package dev.juyoung.unsplash

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.os.Process
import dev.juyoung.unsplash.ui.error.ErrorActivity
import kotlin.system.exitProcess

class RootExceptionHandler(
    private val application: Application,
    private val defaultExceptionHandler: Thread.UncaughtExceptionHandler
) : Thread.UncaughtExceptionHandler {
    private var lastActivity: Activity? = null
    private var activityCount = 0

    private fun isErrorActivity(activity: Activity) = activity is ErrorActivity

    init {
        application.registerActivityLifecycleCallbacks(
            object : Application.ActivityLifecycleCallbacks {
                override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                    if (isErrorActivity(activity)) return

                    lastActivity = activity
                }

                override fun onActivityStarted(activity: Activity) {
                    if (isErrorActivity(activity)) return

                    activityCount++
                    lastActivity = activity
                }

                override fun onActivityResumed(activity: Activity) {
                    /* no-op */
                }

                override fun onActivityPaused(activity: Activity) {
                    /* no-op */
                }

                override fun onActivityStopped(activity: Activity) {
                    if (isErrorActivity(activity)) return

                    activityCount--
                    if (activityCount < 0) {
                        lastActivity = null
                    }
                }

                override fun onActivityDestroyed(activity: Activity) {
                    /* no-op */
                }

                override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
                    /* no-op */
                }
            }
        )
    }

    override fun uncaughtException(thread: Thread, throwable: Throwable) {
        lastActivity?.run {
            Intent(this, ErrorActivity::class.java).run {
                putExtra(ErrorActivity.EXTRA_INTENT, intent)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(this)
                finish()
            }
        } ?: defaultExceptionHandler.uncaughtException(thread, throwable)

        Process.killProcess(Process.myPid())
        exitProcess(-1)
    }
}
