package com.idme.mypurchase.common.dependencyinjection.app

import com.idme.mypurchase.common.Utils
import com.idme.mypurchase.network.PurchaseAPI
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {
    fun getAPIService():PurchaseAPI
    fun getUtils():Utils
}