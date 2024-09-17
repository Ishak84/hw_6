package com.geeks.hw_6

import android.app.Application
import com.geeks.data.di.dataModules
import com.geeks.domain.di.domainModule
import com.geeks.hw_6.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModule, dataModules, domainModule)
        }
    }
}