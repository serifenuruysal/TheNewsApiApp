package com.soulkitchen.serifenuruysal.thenewsapiapp.feature.detail

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.soulkitchen.serifenuruysal.thenewsapiapp.App
import com.soulkitchen.serifenuruysal.thenewsapiapp.R
import com.soulkitchen.serifenuruysal.thenewsapiapp.data.model.Article
import kotlinx.android.synthetic.main.news_item.view.*


/**
 * Created by S.Nur Uysal on 2019-08-26.
 */

class NewsAdapter(val items: ArrayList<Article>, val context: Context, val listener: NewsAdapterInterface) :
    RecyclerView.Adapter<ViewHolder>() {
    lateinit var newsList: MutableSet<String>

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        newsList = App.prefs!!.newsTitles
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.news_item, p0, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, index: Int) {
        Glide.with(context)
            .load(items.get(index).urlToImage)
            .into(holder?.tvNewsImage)
        holder?.tvNewsTitle?.text = items.get(index).title
        holder?.tvNewsContent?.text = items.get(index).description
        holder?.newsCard.setOnClickListener(View.OnClickListener { view -> listener.onClickSource(items.get(index)) })
        holder?.btnSave.setOnClickListener(View.OnClickListener { listener.onClickSave(items.get(index).title) })

        if (isItemSavedBefore(items.get(index).title)) {
            holder?.txtSaved.visibility = View.VISIBLE
            holder?.btnSave.visibility = View.GONE
        } else {
            holder?.txtSaved.visibility = View.GONE
            holder?.btnSave.visibility = View.VISIBLE
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun isItemSavedBefore(title: String): Boolean {
        newsList.forEach { articleTitle: String ->
            if (articleTitle.equals(title))
                return true
        }
        return false
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvNewsImage = view.iv_news_image
    val tvNewsContent = view.tv_news_content
    val newsCard = view.cv_news
    val tvNewsTitle = view.tv_news_title
    val btnSave = view.iv_save_news
    val txtSaved = view.tv_saved_text
}

interface NewsAdapterInterface {
    fun onClickSource(source: Article)
    fun onClickSave(title: String)
}