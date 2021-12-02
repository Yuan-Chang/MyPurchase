package com.idme.mypurchase.common.dependencyinjection.activity

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.idme.mypurchase.common.FetchPurchaseUsecase
import com.idme.mypurchase.common.ImageLoader
import com.idme.mypurchase.common.ScreenNavigator
import com.idme.mypurchase.common.Utils
import com.idme.mypurchase.navigation.NavHostFragmentWrapper
import com.idme.mypurchase.network.PurchaseAPI
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    fun getLayoutInflater() = LayoutInflater.from(activity)

    @Provides
    fun getNavHostFragmentWrapper() = activity as NavHostFragmentWrapper

    @Provides
    fun getNavHostController(navHostFragmentWrapper: NavHostFragmentWrapper) =
        navHostFragmentWrapper.getNavHostFragment()?.findNavController()

    @Provides
    fun screenNavigater(navController: NavController?) = ScreenNavigator(navController)

    @Provides
    fun fetchConnectionUsecase(purchaseAPI: PurchaseAPI, utils: Utils) = FetchPurchaseUsecase(purchaseAPI, utils)

    @Provides
    fun getImageLoader() = ImageLoader()
}