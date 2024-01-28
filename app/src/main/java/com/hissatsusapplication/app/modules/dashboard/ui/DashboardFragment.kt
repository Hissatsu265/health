package com.hissatsusapplication.app.modules.dashboard.ui

import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import com.hissatsusapplication.app.R
import com.hissatsusapplication.app.appcomponents.base.BaseFragment
import com.hissatsusapplication.app.databinding.FragmentDashboardBinding
import com.hissatsusapplication.app.modules.ambulance.ui.AmbulanceActivity
import com.hissatsusapplication.app.modules.article.ui.ArticleActivity
import com.hissatsusapplication.app.modules.dashboard.`data`.model.DoctorlistRowModel
import com.hissatsusapplication.app.modules.dashboard.`data`.model.ThirtylistRowModel
import com.hissatsusapplication.app.modules.dashboard.`data`.viewmodel.DashboardVM
import com.hissatsusapplication.app.modules.drdetails.ui.DrDetailsActivity
import com.hissatsusapplication.app.modules.drlist.ui.DrListActivity
import com.hissatsusapplication.app.modules.pharmacy.ui.PharmacyActivity
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.Unit

class DashboardFragment : BaseFragment<FragmentDashboardBinding>(R.layout.fragment_dashboard) {
  private val viewModel: DashboardVM by viewModels<DashboardVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = arguments
    val thirtyListAdapter = ThirtyListAdapter(viewModel.thirtyListList.value?:mutableListOf())
    binding.recyclerThirtyList.adapter = thirtyListAdapter
    thirtyListAdapter.setOnItemClickListener(
    object : ThirtyListAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : ThirtylistRowModel) {
        onClickRecyclerThirtyList(view, position, item)
      }
    }
    )
    viewModel.thirtyListList.observe(requireActivity()) {
      thirtyListAdapter.updateData(it)
    }
    val doctorListAdapter = DoctorListAdapter(viewModel.doctorListList.value?:mutableListOf())
    binding.recyclerDoctorList.adapter = doctorListAdapter
    doctorListAdapter.setOnItemClickListener(
    object : DoctorListAdapter.OnItemClickListener {
      override fun onItemClick(view:View, position:Int, item : DoctorlistRowModel) {
        onClickRecyclerDoctorList(view, position, item)
      }
    }
    )
    viewModel.doctorListList.observe(requireActivity()) {
      doctorListAdapter.updateData(it)
    }
    binding.dashboardVM = viewModel
    setUpSearchViewSearchListener()
  }

  override fun setUpClicks(): Unit {
    binding.txtSeeAll1.setOnClickListener {
      val destIntent = ArticleActivity.getIntent(requireActivity(), null)
      startActivity(destIntent)
      requireActivity().onBackPressed()
    }
    binding.txtSeeAll.setOnClickListener {
      val destIntent = DrListActivity.getIntent(requireActivity(), null)
      startActivity(destIntent)
      requireActivity().onBackPressed()
    }
  }

  fun onClickRecyclerThirtyList(
    view: View,
    position: Int,
    item: ThirtylistRowModel
  ): Unit {
    when(view.id) {
      R.id.btnTicket ->  {
        onClickRecyclerThirtyListBtnTicket(view, position, item)
      }
    }
  }

  fun onClickRecyclerDoctorList(
    view: View,
    position: Int,
    item: DoctorlistRowModel
  ): Unit {
    when(view.id) {
      R.id.linearDoctor ->  {
        val destIntent = DrDetailsActivity.getIntent(requireActivity(), null)
        startActivity(destIntent)
        requireActivity().onBackPressed()
      }
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

    fun onClickRecyclerThirtyListBtnTicket(
      view: View,
      position: Int,
      item: ThirtylistRowModel
    ) {
      /** TODO As per your logic, Add constant type for item click.*/
      when(0) {
        0 ->  {
          val destIntent = DrListActivity.getIntent(requireActivity(), null)
          startActivity(destIntent)
          requireActivity().onBackPressed()
        }
        1 ->  {
          val destIntent = AmbulanceActivity.getIntent(requireActivity(), null)
          startActivity(destIntent)
          requireActivity().onBackPressed()
        }
        2 ->  {
          val destIntent = ArticleActivity.getIntent(requireActivity(), null)
          startActivity(destIntent)
          requireActivity().onBackPressed()
        }
        3 ->  {
          val destIntent = PharmacyActivity.getIntent(requireActivity(), null)
          startActivity(destIntent)
          requireActivity().onBackPressed()
        }
      }
    }

    companion object {
      const val TAG: String = "DASHBOARD_FRAGMENT"

    }
  }
