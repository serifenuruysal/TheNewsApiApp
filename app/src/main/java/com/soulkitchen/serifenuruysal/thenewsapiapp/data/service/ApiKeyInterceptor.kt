package com.soulkitchen.serifenuruysal.thenewsapiapp.data.service

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by S.Nur Uysal on 2019-08-26.
 */
internal class ApiKeyInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request().newBuilder().addHeader("X-Api-Key", apiKey).build())
    }
}