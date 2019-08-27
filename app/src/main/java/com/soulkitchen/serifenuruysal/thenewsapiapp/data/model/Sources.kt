package com.soulkitchen.serifenuruysal.thenewsapiapp.data.model

import com.google.gson.annotations.SerializedName

data class Sources(
    @SerializedName("status") var status: String,
    @SerializedName("code") var code: String,
    @SerializedName("message")
    var message: String,
    @SerializedName("sources")
    var sources: ArrayList<Source>
)

data class Source(
    @SerializedName("id") var id: String,
    @SerializedName("name") var name: String,
    @SerializedName("description") var description: String,
    @SerializedName("url") var url: String,
    @SerializedName("category") var category: String,
    @SerializedName("language") var language: String,
    @SerializedName("country") var country: String
)