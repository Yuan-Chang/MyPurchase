package com.idme.mypurchase.common.dependencyinjection.app

import android.app.Application
import com.idme.mypurchase.common.Constants
import com.idme.mypurchase.common.Utils
import com.idme.mypurchase.network.PurchaseAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule(private val application: Application) {

    @AppScope
    @Provides
    fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun application() = application

    @AppScope
    @Provides
    fun purchaseService(retrofit: Retrofit) = retrofit.create(PurchaseAPI::class.java)

    @Provides
    fun utils() = Utils()
}