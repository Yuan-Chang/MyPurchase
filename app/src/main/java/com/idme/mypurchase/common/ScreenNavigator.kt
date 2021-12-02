package com.idme.mypurchase.common

import androidx.navigation.NavController
import com.idme.mypurchase.view.profile.ProfileFragmentDirections

class ScreenNavigator(private val navController: NavController?) {
    fun profileToPurchase() {
        val navAction = ProfileFragmentDirections.actionProfileFragmentToPurchaseFragment()
        navController?.navigate(navAction)
    }

    fun navigateUp(){
        navController?.popBackStack()
    }
}