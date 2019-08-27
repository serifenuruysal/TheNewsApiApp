package com.soulkitchen.serifenuruysal.thenewsapiapp.feature.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.util.Log
import com.soulkitchen.serifenuruysal.thenewsapiapp.R
import com.soulkitchen.serifenuruysal.thenewsapiapp.data.model.Article
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_source_detail.*
import java.util.concurrent.TimeUnit

class SourceDetailActivity : AppCompatActivity(), NewsAdapterInterface {

    lateinit var sourceId: String
    private lateinit var binding: ViewDataBinding
    private lateinit var viewModel: SourceDetailViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate view and obtain an instance of the binding class.
        binding = DataBindingUtil.setContentView<ViewDataBinding>(this, R.layout.activity_source_detail)

        // Specify the current activity as the lifecycle owner.
        binding.setLifecycleOwner(this)

        viewModel = ViewModelProviders.of(this).get(SourceDetailViewModel::class.java)

        var mTopToolbar = findViewById<Toolbar>(R.id.toolbar);
        setSupportActionBar(mTopToolbar);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            sourceId = bundle.getString("source_id")

        }
        rv_news_list.layoutManager = LinearLayoutManager(this)

        viewModel.articleList.observe(this, Observer { articleList ->
            rv_news_list.adapter =
                articleList?.let { NewsAdapter(it, this, this) }
        })

        viewModel.loadPosts(sourceId)

        //Update news every 60 seconds periodically
        Observable.interval(60, TimeUnit.SECONDS, Schedulers.io())
            .subscribe({
                viewModel.loadPosts(sourceId)
                Log.d("SourceDetailActivity", "news updated")
            })


    }

    override fun onClickSource(article: Article) {
        val openURL = Intent(Intent.ACTION_VIEW)
        openURL.data = Uri.parse(article.url)
        startActivity(openURL)
    }


    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }

}
