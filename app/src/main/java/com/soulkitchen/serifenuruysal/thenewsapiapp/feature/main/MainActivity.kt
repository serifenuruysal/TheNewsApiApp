package com.soulkitchen.serifenuruysal.thenewsapiapp.feature.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.soulkitchen.serifenuruysal.thenewsapiapp.R
import com.soulkitchen.serifenuruysal.thenewsapiapp.data.model.Source
import com.soulkitchen.serifenuruysal.thenewsapiapp.feature.detail.SourceDetailActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SourceAdapterInterface {

    private lateinit var binding: ViewDataBinding
    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate view and obtain an instance of the binding class.
        binding = DataBindingUtil.setContentView<ViewDataBinding>(this, R.layout.activity_main)

        // Specify the current activity as the lifecycle owner.
        binding.setLifecycleOwner(this)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        rv_source_list.layoutManager = LinearLayoutManager(this)

        viewModel.sourceList.observe(this, Observer { sourceList ->
            rv_source_list.adapter = sourceList?.let { SourceAdapter(it, this, this) }
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }

    override fun onClickSource(source: Source) {
        val intent = Intent(this, SourceDetailActivity::class.java)
        intent.putExtra("source_id", source.id)
        startActivity(intent)
    }


}
