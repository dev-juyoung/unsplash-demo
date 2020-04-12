package dev.juyoung.unsplash

import android.app.Application
import dev.juyoung.unsplash.di.networkModule
import dev.juyoung.unsplash.di.repositoryModule
import dev.juyoung.unsplash.di.viewModelModule
import dev.juyoung.unsplash.extensions.isDebuggable
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class RootApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        setupDevTools()
        setupDI()
        setupExceptionHandler()
    }

    private fun setupDevTools() {
        if (!isDebuggable) return

        Timber.plant(Timber.DebugTree())
    }

    private fun setupDI() {
        startKoin {
            androidLogger()
            androidContext(this@RootApplication)
            modules(listOf(networkModule, repositoryModule, viewModelModule))
        }
    }

    private fun setupExceptionHandler() {
        val exceptionHandler = RootExceptionHandler(
            this,
            Thread.getDefaultUncaughtExceptionHandler()!!
        )

        Thread.setDefaultUncaughtExceptionHandler(exceptionHandler)
    }
}
