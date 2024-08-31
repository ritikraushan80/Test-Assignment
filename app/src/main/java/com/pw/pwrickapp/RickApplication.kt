package com.pw.pwrickapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


/**
 * Created by Ritik on: 31/08/24
 */

@HiltAndroidApp
class RickApplication : Application() {
    override fun onCreate() {
        super.onCreate()

    }
}