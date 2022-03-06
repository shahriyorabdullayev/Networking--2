package com.example.modul6networking.model

import com.google.gson.annotations.SerializedName

data class PosterResp(

    @SerializedName("id")
    val id: Int,
    @SerializedName("userId")
    val userId: Int = 0,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("body")
    val body: String? = null
)
