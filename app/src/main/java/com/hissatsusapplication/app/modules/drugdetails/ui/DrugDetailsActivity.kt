package com.hissatsusapplication.app.modules.drugdetails.ui

import androidx.activity.viewModels
import com.hissatsusapplication.app.R
import com.hissatsusapplication.app.appcomponents.base.BaseActivity
import com.hissatsusapplication.app.databinding.ActivityDrugDetailsBinding
import com.hissatsusapplication.app.modules.cart.ui.CartActivity
import com.hissatsusapplication.app.modules.drugdetails.`data`.viewmodel.DrugDetailsVM
import kotlin.String
import kotlin.Unit

class DrugDetailsActivity : BaseActivity<ActivityDrugDetailsBinding>(R.layout.activity_drug_details)
    {
  private val viewModel: DrugDetailsVM by viewModels<DrugDetailsVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.drugDetailsVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.btnBuyNow.setOnClickListener {
      val destIntent = CartActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.imageCart.setOnClickListener {
      val destIntent = CartActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.imageArrowLeft.setOnClickListener {
      finish()
    }
  }

  companion object {
    const val TAG: String = "DRUG_DETAILS_ACTIVITY"

  }
}
