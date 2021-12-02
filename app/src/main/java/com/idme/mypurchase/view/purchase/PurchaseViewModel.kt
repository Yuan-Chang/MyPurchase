package com.idme.mypurchase.view.purchase


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.idme.mypurchase.common.Constants
import com.idme.mypurchase.common.FetchPurchaseUsecase
import com.idme.mypurchase.network.PurchaseSchema
import javax.inject.Inject

class PurchaseViewModel @Inject constructor(val fetchPurchaseUsecase: FetchPurchaseUsecase) : ViewModel() {

    val state = MutableLiveData<Constants.State>().apply { Constants.State.NORMAL }
    val purchaseList = MutableLiveData<List<PurchaseSchema>?>().apply { value = null }

    fun fetchPurchase(){

        if (purchaseList.value == null){
            state.value = Constants.State.IN_PROGRESS
            fetchPurchaseUsecase.fetchPurchase{ result, error ->
                if (result != null){
                    state.postValue(Constants.State.NORMAL)
                    purchaseList.postValue(result)
                } else {
                    state.postValue(Constants.State.ERROR)
                }
            }
        }
    }
}