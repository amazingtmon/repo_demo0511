package com.example.ktbook802.model

import com.google.gson.annotations.SerializedName

data class Book (
    //매핑해서 값을 가져오기 위해 @SerializedName
    @SerializedName("itemId") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("coverSmallUrl") val coverSmallUrl: String,

)