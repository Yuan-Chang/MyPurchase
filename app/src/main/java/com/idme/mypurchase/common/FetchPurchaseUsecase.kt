package com.idme.mypurchase.common

import com.idme.mypurchase.network.ProfileSchema
import com.idme.mypurchase.network.PurchaseAPI
import com.idme.mypurchase.network.PurchaseSchema
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FetchPurchaseUsecase(val purchaseAPI: PurchaseAPI, val utils: Utils) {
    fun fetchProfile(callback: (ProfileSchema?, error: Throwable?) -> Unit){
        purchaseAPI.fetchProfile().enqueue(object: Callback<ProfileSchema?> {
            override fun onResponse(
                call: Call<ProfileSchema?>,
                response: Response<ProfileSchema?>
            ) {

                val res = response.body()
                if (res != null){
                    res.formattedNumber = utils.formatPhoneNumber(res.phoneNumber)
                    res.formattedDate = utils.dateStringToDate(res.registrationDate)
                    callback.invoke(res, null)
                } else {
                    callback.invoke(null,Throwable("Fail to fetch ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<ProfileSchema?>, t: Throwable) {
                callback.invoke(null,t)
            }

        })
    }

    fun fetchPurchase(callback: (List<PurchaseSchema>?, error:Throwable?) -> Unit){
        purchaseAPI.fetchPurchase().enqueue(object: Callback<List<PurchaseSchema>?>{
            override fun onResponse(
                call: Call<List<PurchaseSchema>?>,
                response: Response<List<PurchaseSchema>?>
            ) {
                val res = response.body()
                if (res != null){
                    res.forEach {
                        it.formattedPurchaseDate = utils.dateStringToDate(it.purchaseDate)
                    }
                    callback.invoke(res, null)
                } else {
                    callback.invoke(null,Throwable("Fail to fetch ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<List<PurchaseSchema>?>, t: Throwable) {
                callback.invoke(null,t)
            }
        })
    }
}