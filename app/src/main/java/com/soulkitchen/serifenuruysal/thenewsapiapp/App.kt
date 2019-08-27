package com.soulkitchen.serifenuruysal.thenewsapiapp

import android.app.Application
import com.soulkitchen.serifenuruysal.thenewsapiapp.data.local.PreferenceHelper

/**
 * Created by S.Nur Uysal on 2019-08-27.
 */
class App : Application() {
    companion object {
        var prefs: PreferenceHelper? = null
    }

    override fun onCreate() {
        prefs = PreferenceHelper(applicationContext)
        super.onCreate()
    }
}