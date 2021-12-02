package com.example.lampa

import android.app.Application
import com.example.lampa.di.AppComponent
import com.example.lampa.di.DaggerAppComponent
import com.example.lampa.di.module.GeneralModule

class App : Application() {
    private lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().generalModule(GeneralModule()).build()
    }

    fun getAppComponent() = appComponent
}