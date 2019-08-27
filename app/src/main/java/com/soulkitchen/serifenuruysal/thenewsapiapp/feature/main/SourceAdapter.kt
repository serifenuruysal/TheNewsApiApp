package com.soulkitchen.serifenuruysal.thenewsapiapp.feature.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.soulkitchen.serifenuruysal.thenewsapiapp.R
import com.soulkitchen.serifenuruysal.thenewsapiapp.data.model.Source
import kotlinx.android.synthetic.main.source_item.view.*


/**
 * Created by S.Nur Uysal on 2019-08-26.
 */

class SourceAdapter(val items: ArrayList<Source>, val context: Context, val listener: SourceAdapterInterface) :
    RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.source_item, p0, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, p1: Int) {
        holder?.tvSourceName?.text = items.get(p1).name
        holder?.sourceCard.setOnClickListener(View.OnClickListener { view -> listener.onClickSource(items.get(p1)) })

    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val tvSourceName = view.tv_source_name
    val sourceCard = view.card_view
}

interface SourceAdapterInterface {
    fun onClickSource(source: Source)
}