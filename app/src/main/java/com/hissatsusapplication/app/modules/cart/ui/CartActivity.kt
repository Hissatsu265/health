package com.hissatsusapplication.app.modules.cart.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.hissatsusapplication.app.R
import com.hissatsusapplication.app.appcomponents.base.BaseActivity
import com.hissatsusapplication.app.databinding.ActivityCartBinding
import com.hissatsusapplication.app.modules.cart.`data`.model.DrugslistRowModel
import com.hissatsusapplication.app.modules.cart.`data`.viewmodel.CartVM
import kotlin.Int
import kotlin.String
import kotlin.Unit

class CartActivity : BaseActivity<ActivityCartBinding>(R.layout.activity_cart) {
  private val viewModel: CartVM by viewModels<CartVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val drugsListAdapter = DrugsListAdapter(viewModel.drugsListList.value?:mutableListOf())
    binding.recyclerDrugsList.adapter = drugsListAdapter
    drugsListAdapter.setOnItemClickListener(
    object : DrugsListAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : DrugslistRowModel) {
        onClickRecyclerDrugsList(view, position, item)
      }
    }
    )
    viewModel.drugsListList.observe(this) {
      drugsListAdapter.updateData(it)
    }
    binding.cartVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowLeft.setOnClickListener {
      finish()
    }
  }

  fun onClickRecyclerDrugsList(
    view: View,
    position: Int,
    item: DrugslistRowModel
  ): Unit {
    when(view.id) {
    }
  }

  companion object {
    const val TAG: String = "CART_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, CartActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
