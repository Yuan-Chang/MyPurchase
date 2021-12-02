package com.idme.mypurchase.common.base

import androidx.appcompat.app.AppCompatActivity
import com.idme.mypurchase.common.dependencyinjection.activity.ActivityModule
import com.idme.mypurchase.MyApplication
import com.idme.mypurchase.common.dependencyinjection.activity.DaggerActivityComponent

open class BaseActivity : AppCompatActivity() {

    private val appComponent get() = (application as MyApplication).appComponent

    val activityComponent by lazy {

        DaggerActivityComponent.builder().appComponent(appComponent)
            .activityModule(ActivityModule(this)).build()
    }

    open fun onFragmentStart(baseFragment: BaseFragment) {}

    val injector get() = activityComponent
}