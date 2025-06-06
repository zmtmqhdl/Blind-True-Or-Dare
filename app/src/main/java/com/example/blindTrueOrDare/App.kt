package com.example.blindTrueOrDare

import android.app.Application
import com.example.blindTrueOrDare.manager.NetworkManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {
    override fun onCreate() {
        super.onCreate()
        NetworkManager.initialize(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        NetworkManager.release()
    }
}