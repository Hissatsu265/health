package com.hissatsusapplication.app.modules.pharmacy.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hissatsusapplication.app.modules.pharmacy.`data`.model.Drugs1RowModel
import com.hissatsusapplication.app.modules.pharmacy.`data`.model.DrugsRowModel
import com.hissatsusapplication.app.modules.pharmacy.`data`.model.PharmacyModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class PharmacyVM : ViewModel(), KoinComponent {
  val pharmacyModel: MutableLiveData<PharmacyModel> = MutableLiveData(PharmacyModel())

  var navArguments: Bundle? = null

  val drugsList: MutableLiveData<MutableList<DrugsRowModel>> = MutableLiveData(mutableListOf())

  val drugs1List: MutableLiveData<MutableList<Drugs1RowModel>> = MutableLiveData(mutableListOf())
}
