package com.hissatsusapplication.app.modules.drugdetails.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hissatsusapplication.app.modules.drugdetails.`data`.model.DrugDetailsModel
import org.koin.core.KoinComponent

class DrugDetailsVM : ViewModel(), KoinComponent {
  val drugDetailsModel: MutableLiveData<DrugDetailsModel> = MutableLiveData(DrugDetailsModel())

  var navArguments: Bundle? = null
}
