package com.hissatsusapplication.app.modules.dashboard.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hissatsusapplication.app.modules.dashboard.`data`.model.DashboardModel
import com.hissatsusapplication.app.modules.dashboard.`data`.model.DoctorlistRowModel
import com.hissatsusapplication.app.modules.dashboard.`data`.model.ThirtylistRowModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class DashboardVM : ViewModel(), KoinComponent {
  val dashboardModel: MutableLiveData<DashboardModel> = MutableLiveData(DashboardModel())

  var navArguments: Bundle? = null

  val thirtyListList: MutableLiveData<MutableList<ThirtylistRowModel>> =
      MutableLiveData(mutableListOf())

  val doctorListList: MutableLiveData<MutableList<DoctorlistRowModel>> =
      MutableLiveData(mutableListOf())
}
