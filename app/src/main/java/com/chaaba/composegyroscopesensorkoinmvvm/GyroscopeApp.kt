package com.chaaba.composegyroscopesensorkoinmvvm

import android.app.Application
import com.chaaba.composegyroscopesensorkoinmvvm.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GyroscopeApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
            androidContext(this@GyroscopeApp)
        }
    }
}