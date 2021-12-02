package com.idme.mypurchase.network

import com.google.gson.annotations.SerializedName

data class PurchaseSchema(
    @SerializedName("image")
    val image: String,
    @SerializedName("purchase_date")
    val purchaseDate: String,
    @SerializedName("item_name")
    val itemName: String,
    @SerializedName("price")
    val price: Float,
    @SerializedName("serial")
    val serialNumber: String,
    @SerializedName("description")
    val description: String,

    var formattedPurchaseDate: String?,
    var isOpen: Boolean = false
)

