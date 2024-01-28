package com.hissatsusapplication.app.modules.drdetails.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hissatsusapplication.app.modules.drdetails.`data`.model.DrDetailsModel
import org.koin.core.KoinComponent

class DrDetailsVM : ViewModel(), KoinComponent {
  val drDetailsModel: MutableLiveData<DrDetailsModel> = MutableLiveData(DrDetailsModel())

  var navArguments: Bundle? = null
}
