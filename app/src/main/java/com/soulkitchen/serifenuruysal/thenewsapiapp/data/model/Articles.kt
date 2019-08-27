package com.soulkitchen.serifenuruysal.thenewsapiapp.data.model

import com.google.gson.annotations.SerializedName

data class Articles(
    @SerializedName("status") var status: String,
    @SerializedName("code") var code: String,
    @SerializedName("message") var message: String,
    @SerializedName("totalResults") var totalResults: Int,
    @SerializedName("articles") var articles: ArrayList<Article>
)

data class Article(
    @SerializedName("source") var source: Source,
    @SerializedName("author") var author: String,
    @SerializedName("title") var title: String,
    @SerializedName("description") var description: String,
    @SerializedName("url") var url: String,
    @SerializedName("urlToImage") var urlToImage: String,
    @SerializedName("publishedAt") var publishedAt: String
)