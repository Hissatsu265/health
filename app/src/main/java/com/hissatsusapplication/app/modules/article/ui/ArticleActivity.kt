package com.hissatsusapplication.app.modules.article.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import com.hissatsusapplication.app.R
import com.hissatsusapplication.app.appcomponents.base.BaseActivity
import com.hissatsusapplication.app.databinding.ActivityArticleBinding
import com.hissatsusapplication.app.modules.article.`data`.model.RelatedarticlesRowModel
import com.hissatsusapplication.app.modules.article.`data`.model.TrendingsRowModel
import com.hissatsusapplication.app.modules.article.`data`.viewmodel.ArticleVM
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.Unit

class ArticleActivity : BaseActivity<ActivityArticleBinding>(R.layout.activity_article) {
  private val viewModel: ArticleVM by viewModels<ArticleVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val trendingsAdapter = TrendingsAdapter(viewModel.trendingsList.value?:mutableListOf())
    binding.recyclerTrendings.adapter = trendingsAdapter
    trendingsAdapter.setOnItemClickListener(
    object : TrendingsAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : TrendingsRowModel) {
        onClickRecyclerTrendings(view, position, item)
      }
    }
    )
    viewModel.trendingsList.observe(this) {
      trendingsAdapter.updateData(it)
    }
    val relatedArticlesAdapter =
    RelatedArticlesAdapter(viewModel.relatedArticlesList.value?:mutableListOf())
    binding.recyclerRelatedArticles.adapter = relatedArticlesAdapter
    relatedArticlesAdapter.setOnItemClickListener(
    object : RelatedArticlesAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : RelatedarticlesRowModel) {
        onClickRecyclerRelatedArticles(view, position, item)
      }
    }
    )
    viewModel.relatedArticlesList.observe(this) {
      relatedArticlesAdapter.updateData(it)
    }
    binding.articleVM = viewModel
    setUpSearchViewSearchListener()
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowLeft.setOnClickListener {
      finish()
    }
  }

  fun onClickRecyclerTrendings(
    view: View,
    position: Int,
    item: TrendingsRowModel
  ): Unit {
    when(view.id) {
    }
  }

  fun onClickRecyclerRelatedArticles(
    view: View,
    position: Int,
    item: RelatedarticlesRowModel
  ): Unit {
    when(view.id) {
    }
  }

  private fun setUpSearchViewSearchListener(): Unit {
    binding.searchViewSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
      override fun onQueryTextSubmit(p0 : String) : Boolean {
        // Performs search when user hit
        // the search button on the keyboard
        return false
      }
      override fun onQueryTextChange(p0 : String) : Boolean {
        // Start filtering the list as user
        // start entering the characters
        return false
      }
      })
    }

    companion object {
      const val TAG: String = "ARTICLE_ACTIVITY"


      fun getIntent(context: Context, bundle: Bundle?): Intent {
        val destIntent = Intent(context, ArticleActivity::class.java)
        destIntent.putExtra("bundle", bundle)
        return destIntent
      }
    }
  }
