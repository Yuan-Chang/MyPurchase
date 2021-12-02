package com.idme.mypurchase.common.base

import androidx.fragment.app.Fragment

open class BaseFragment: Fragment() {

    private val activityComponent by lazy {
        (requireActivity() as BaseActivity).activityComponent
    }

    val injector get() = activityComponent

    override fun onStart() {
        super.onStart()
        (requireActivity() as BaseActivity).onFragmentStart(this)
    }
}