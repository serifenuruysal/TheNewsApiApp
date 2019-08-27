package com.soulkitchen.serifenuruysal.thenewsapiapp.data.service

import com.soulkitchen.serifenuruysal.thenewsapiapp.data.model.Articles
import com.soulkitchen.serifenuruysal.thenewsapiapp.data.model.Sources
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by S.Nur Uysal on 2019-08-26.
 */
internal interface NewsApiService {

    @GET("v2/sources")
    fun getSources(): Single<Sources>

    @GET("v2/top-headlines")
    fun getTopHeadLines(  @Query("sources") sources: String?): Single<Articles>

}