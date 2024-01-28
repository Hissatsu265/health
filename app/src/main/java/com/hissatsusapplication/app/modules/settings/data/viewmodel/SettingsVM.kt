package com.hissatsusapplication.app.modules.settings.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hissatsusapplication.app.modules.settings.`data`.model.SettingsModel
import com.hissatsusapplication.app.modules.settings.`data`.model.SettingsRowModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class SettingsVM : ViewModel(), KoinComponent {
  val settingsModel: MutableLiveData<SettingsModel> = MutableLiveData(SettingsModel())

  var navArguments: Bundle? = null

  val settingsList: MutableLiveData<MutableList<SettingsRowModel>> =
      MutableLiveData(mutableListOf())
}
