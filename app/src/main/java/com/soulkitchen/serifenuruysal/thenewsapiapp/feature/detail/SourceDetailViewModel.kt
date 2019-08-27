package com.soulkitchen.serifenuruysal.thenewsapiapp.feature.detail

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.soulkitchen.serifenuruysal.thenewsapiapp.data.model.Article
import com.soulkitchen.serifenuruysal.thenewsapiapp.data.service.NewsApiRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by S.Nur Uysal on 2019-08-26.
 */
class SourceDetailViewModel() : ViewModel() {

    val articleList: MutableLiveData<ArrayList<Article>> = MutableLiveData()


    private lateinit var subscription: Disposable


    fun loadPosts(sourceId: String) {
        val newsApiRepository = NewsApiRepository()
        subscription = newsApiRepository.getTopHeadLines(sourceId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toObservable().subscribe({ articleRes ->
                articleList.postValue(articleRes.articles)

            },
                { error -> Log.d("getArticle error", error.message) })


    }

    fun onDestroy() {
        subscription.dispose()
    }

}
