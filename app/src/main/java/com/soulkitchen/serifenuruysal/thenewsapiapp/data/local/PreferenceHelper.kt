package com.soulkitchen.serifenuruysal.thenewsapiapp.data.local

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by S.Nur Uysal on 2019-08-27.
 */

class PreferenceHelper(context: Context) {

    val NEWS_TITLE = "NEWS_TITLE"

    val PREFS_FILENAME = "com.soulkitchen.serifenuruysal.thenewsapiapp.prefs"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0);

    var newsTitles: MutableSet<String>
        get() = prefs.getStringSet(NEWS_TITLE, emptySet<String>())
        set(value) = prefs.edit().putStringSet(NEWS_TITLE, value).apply()
}