package com.example.myselfapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MySelfApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}