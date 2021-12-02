package com.idme.mypurchase.network

import android.icu.number.FormattedNumber
import com.google.gson.annotations.SerializedName

data class ProfileSchema(
    @SerializedName("name")
    val name: String,
    @SerializedName("user_name")
    val userName: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("registration")
    val registrationDate: String,
    @SerializedName("image")
    val imageUrl: String,

    var formattedNumber: String? = null,
    var formattedDate: String? = null

)

