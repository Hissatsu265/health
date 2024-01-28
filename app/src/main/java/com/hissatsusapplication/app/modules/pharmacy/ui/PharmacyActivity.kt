package com.hissatsusapplication.app.modules.pharmacy.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import com.hissatsusapplication.app.R
import com.hissatsusapplication.app.appcomponents.base.BaseActivity
import com.hissatsusapplication.app.databinding.ActivityPharmacyBinding
import com.hissatsusapplication.app.modules.cart.ui.CartActivity
import com.hissatsusapplication.app.modules.pharmacy.`data`.model.Drugs1RowModel
import com.hissatsusapplication.app.modules.pharmacy.`data`.model.DrugsRowModel
import com.hissatsusapplication.app.modules.pharmacy.`data`.viewmodel.PharmacyVM
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.Unit

class PharmacyActivity : BaseActivity<ActivityPharmacyBinding>(R.layout.activity_pharmacy) {
  private val viewModel: PharmacyVM by viewModels<PharmacyVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val drugsAdapter = DrugsAdapter(viewModel.drugsList.value?:mutableListOf())
    binding.recyclerDrugs.adapter = drugsAdapter
    drugsAdapter.setOnItemClickListener(
    object : DrugsAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : DrugsRowModel) {
        onClickRecyclerDrugs(view, position, item)
      }
    }
    )
    viewModel.drugsList.observe(this) {
      drugsAdapter.updateData(it)
    }
    val drugs1Adapter = Drugs1Adapter(viewModel.drugs1List.value?:mutableListOf())
    binding.recyclerDrugs1.adapter = drugs1Adapter
    drugs1Adapter.setOnItemClickListener(
    object : Drugs1Adapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : Drugs1RowModel) {
        onClickRecyclerDrugs1(view, position, item)
      }
    }
    )
    viewModel.drugs1List.observe(this) {
      drugs1Adapter.updateData(it)
    }
    binding.pharmacyVM = viewModel
    setUpSearchViewSearchListener()
  }

  override fun setUpClicks(): Unit {
    binding.imageCart.setOnClickListener {
      val destIntent = CartActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.imageArrowLeft.setOnClickListener {
      finish()
    }
  }

  fun onClickRecyclerDrugs(
    view: View,
    position: Int,
    item: DrugsRowModel
  ): Unit {
    when(view.id) {
    }
  }

  fun onClickRecyclerDrugs1(
    view: View,
    position: Int,
    item: Drugs1RowModel
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
      const val TAG: String = "PHARMACY_ACTIVITY"


      fun getIntent(context: Context, bundle: Bundle?): Intent {
        val destIntent = Intent(context, PharmacyActivity::class.java)
        destIntent.putExtra("bundle", bundle)
        return destIntent
      }
    }
  }
