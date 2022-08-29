package com.esrakaya.userlistapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class UserListApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}