package com.hissatsusapplication.app.modules.ambulance.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hissatsusapplication.app.modules.ambulance.`data`.model.AmbulanceModel
import org.koin.core.KoinComponent

class AmbulanceVM : ViewModel(), KoinComponent {
  val ambulanceModel: MutableLiveData<AmbulanceModel> = MutableLiveData(AmbulanceModel())

  var navArguments: Bundle? = null
}
