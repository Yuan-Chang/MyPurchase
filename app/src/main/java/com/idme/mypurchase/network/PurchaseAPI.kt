package com.idme.mypurchase.network

import retrofit2.Call
import retrofit2.http.*

interface PurchaseAPI {
    @GET("/profile/U13023932")
    fun fetchProfile(): Call<ProfileSchema?>

    @GET("/purchases/U13023932?page=1")
    fun fetchPurchase(): Call<List<PurchaseSchema>?>
}
