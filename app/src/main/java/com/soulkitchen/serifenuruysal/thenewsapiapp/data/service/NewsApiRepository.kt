package com.soulkitchen.serifenuruysal.thenewsapiapp.data.service

import com.soulkitchen.serifenuruysal.thenewsapiapp.data.model.Articles
import com.soulkitchen.serifenuruysal.thenewsapiapp.data.model.Sources
import com.soulkitchen.serifenuruysal.thenewsapiapp.di.Injector
import io.reactivex.Single

/**
 * Created by S.Nur Uysal on 2019-08-26.
 */
class NewsApiRepository() {
    private val apiKey = "0eaabf95375845adac3b30d22fff6aca"

    private val injector = Injector(apiKey)
    private val newsApiService: NewsApiService by injector.instance()

    fun getSources(): Single<Sources> {
        return newsApiService.getSources()
    }

    fun getTopHeadLines(id: String): Single<Articles> {
        return newsApiService.getTopHeadLines(id)
    }

}