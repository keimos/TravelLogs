package com.keimos.travellogs

import android.app.Application

class AndroidGettingStartedApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // initialize Amplify on startup
        Backend.initialize(applicationContext)
    }
}