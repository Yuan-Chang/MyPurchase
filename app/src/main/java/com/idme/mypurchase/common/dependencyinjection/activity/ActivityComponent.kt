package com.idme.mypurchase.common.dependencyinjection.activity

import com.idme.mypurchase.MainActivity
import com.idme.mypurchase.common.ScreenNavigator
import com.idme.mypurchase.common.dependencyinjection.app.AppComponent
import com.idme.mypurchase.view.profile.ProfileFragment
import com.idme.mypurchase.view.purchase.PurchaseFragment
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun getScreenNavigator(): ScreenNavigator

    fun inject(activity: MainActivity)
    fun inject(purchaseFragment: PurchaseFragment)
    fun inject(profileFragment: ProfileFragment)
}