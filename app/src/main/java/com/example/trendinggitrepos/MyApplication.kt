package com.example.trendinggitrepos

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class MyApplication : DaggerApplication() {

    init {

        application = this
    }

    companion object {

        lateinit var application: MyApplication
    }


        override fun applicationInjector(): AndroidInjector<out DaggerApplication> {


            val component = DaggerApplicationComponent.builder().application(this)
                .build()
            component.inject( this)
            // add database to component builder

            return component
        }


    }