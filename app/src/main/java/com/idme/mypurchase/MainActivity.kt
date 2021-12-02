package com.idme.mypurchase

import android.os.Bundle
import android.view.View
import com.idme.mypurchase.common.base.BaseActivity
import com.idme.mypurchase.common.base.BaseFragment
import com.idme.mypurchase.navigation.NavHostFragmentWrapper

class MainActivity : BaseActivity(), NavHostFragmentWrapper {

    var currentFragment: BaseFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun getNavHostFragment() = findViewById<View>(R.id.navHostFragment)

    // Keep track of current fragment
    override fun onFragmentStart(baseFragment: BaseFragment) {
        super.onFragmentStart(baseFragment)
        currentFragment = baseFragment
    }
}