package com.idme.mypurchase

import android.app.Application
import com.idme.mypurchase.common.dependencyinjection.app.AppModule
import com.idme.mypurchase.common.dependencyinjection.app.DaggerAppComponent

class MyApplication : Application() {
    val appComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

}