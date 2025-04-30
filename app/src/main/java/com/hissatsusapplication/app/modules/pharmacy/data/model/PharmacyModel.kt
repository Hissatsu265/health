package com.hissatsusapplication.app.modules.pharmacy.`data`.model

import com.hissatsusapplication.app.R
import com.hissatsusapplication.app.appcomponents.di.MyApp
import kotlin.String

data class PharmacyModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtTopDoctor: String? = MyApp.getInstance().resources.getString(R.string.lbl_pharmacy)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtEarlyProtection: String? =
      MyApp.getInstance().resources.getString(R.string.msg_order_quickly_w)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtProductText: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_popular_product)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtSeeAllText: String? = MyApp.getInstance().resources.getString(R.string.lbl_see_all)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtProductText1: String? =
      MyApp.getInstance().resources.getString(R.string.lbl_product_on_sale)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtSeeAllText1: String? = MyApp.getInstance().resources.getString(R.string.lbl_see_all)

)
