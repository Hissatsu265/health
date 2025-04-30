package com.hissatsusapplication.app.modules.drlist.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.hissatsusapplication.app.R
import com.hissatsusapplication.app.appcomponents.base.BaseActivity
import com.hissatsusapplication.app.databinding.ActivityDrListBinding
import com.hissatsusapplication.app.modules.drlist.`data`.model.DrlistRowModel
import com.hissatsusapplication.app.modules.drlist.`data`.viewmodel.DrListVM
import kotlin.Int
import kotlin.String
import kotlin.Unit

class DrListActivity : BaseActivity<ActivityDrListBinding>(R.layout.activity_dr_list) {
  private val viewModel: DrListVM by viewModels<DrListVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    val drListAdapter = DrListAdapter(viewModel.drListList.value?:mutableListOf())
    binding.recyclerDrList.adapter = drListAdapter
    drListAdapter.setOnItemClickListener(
    object : DrListAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : DrlistRowModel) {
        onClickRecyclerDrList(view, position, item)
      }
    }
    )
    viewModel.drListList.observe(this) {
      drListAdapter.updateData(it)
    }
    binding.drListVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.imageArrowLeft.setOnClickListener {
      finish()
    }
  }

  fun onClickRecyclerDrList(
    view: View,
    position: Int,
    item: DrlistRowModel
  ): Unit {
    when(view.id) {
    }
  }

  companion object {
    const val TAG: String = "DR_LIST_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, DrListActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
