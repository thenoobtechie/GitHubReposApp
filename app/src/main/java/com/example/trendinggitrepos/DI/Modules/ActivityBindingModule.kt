package com.app.nasatask.DI.Modules

import com.example.trendinggitrepos.MainActivity
import com.example.trendinggitrepos.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun contributesMainActivity(): MainActivity
//
//    @ContributesAndroidInjector(modules = [ContributorModule::class])
//    abstract fun contributesContributorsDetailsActivity(): ContributorsDetailsActivity
}