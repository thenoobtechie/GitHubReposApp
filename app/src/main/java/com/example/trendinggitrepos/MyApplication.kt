package com.example.trendinggitrepos

import android.app.Application

class MyApplication : Application() {

    init {

        application = this
    }

    companion object {

        lateinit var application: MyApplication
    }
}