package com.soulkitchen.serifenuruysal.thenewsapiapp.feature.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.soulkitchen.serifenuruysal.thenewsapiapp.data.model.Source
import com.soulkitchen.serifenuruysal.thenewsapiapp.data.service.NewsApiRepository
import com.soulkitchen.serifenuruysal.thenewsapiapp.feature.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by S.Nur Uysal on 2019-08-26.
 */
class MainViewModel() : ViewModel() {

    val sourceList: MutableLiveData<ArrayList<Source>> = MutableLiveData()


    private lateinit var subscription: Disposable

    init {
        loadPosts()
    }

    private fun loadPosts() {
        val newsApiRepository = NewsApiRepository()
        subscription = newsApiRepository.getSources()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toObservable().subscribe({ sourcesRes ->
                sourceList.postValue(sourcesRes.sources)

            },
                { error -> Log.d("getSources error", error.message) })


    }

    fun onDestroy() {
        subscription.dispose()
    }

}
