package com.draganstojanov.myworld_compose

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MyWorldApplication : Application() {

    companion object {
        lateinit var instance: MyWorldApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}