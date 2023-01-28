package com.example.testmodulotech.app

import android.app.Application
import com.example.testmodulotech.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TestModuloTechApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TestModuloTechApplication)
            modules(appModule)
        }
    }
}