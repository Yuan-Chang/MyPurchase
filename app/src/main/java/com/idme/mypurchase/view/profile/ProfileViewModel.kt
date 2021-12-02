package com.idme.mypurchase.view.profile


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.idme.mypurchase.common.Constants
import com.idme.mypurchase.common.FetchPurchaseUsecase
import com.idme.mypurchase.network.ProfileSchema
import javax.inject.Inject

class ProfileViewModel @Inject constructor(val fetchPurchaseUsecase: FetchPurchaseUsecase) : ViewModel() {

    var state = MutableLiveData<Constants.State>().apply { Constants.State.NORMAL }
    var userProfile = MutableLiveData<ProfileSchema?>()

    fun fetchProfile(){
        if (userProfile.value == null){
            state.value = Constants.State.IN_PROGRESS

            fetchPurchaseUsecase.fetchProfile{ profile,error->

                if (profile != null){
                    state.value = Constants.State.NORMAL
                    userProfile.postValue(profile)
                } else {
                    state.value = Constants.State.ERROR
                }

            }
        }
    }
}