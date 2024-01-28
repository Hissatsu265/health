package com.hissatsusapplication.app.modules.settings.`data`.model

import com.hissatsusapplication.app.R
import com.hissatsusapplication.app.appcomponents.di.MyApp
import kotlin.String

data class SettingsModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtAmeliaRenata: String? = MyApp.getInstance().resources.getString(R.string.lbl_amelia_renata)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtAppointment: String? = MyApp.getInstance().resources.getString(R.string.lbl_my_saved)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtAppointment1: String? = MyApp.getInstance().resources.getString(R.string.lbl_appointment)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtAppointment2: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_payment_method)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtAppointment3: String? = MyApp.getInstance().resources.getString(R.string.lbl_faqs)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtAppointment4: String? = MyApp.getInstance().resources.getString(R.string.lbl_help)

)
