package com.hissatsusapplication.app.modules.signup.`data`.model

import com.hissatsusapplication.app.R
import com.hissatsusapplication.app.appcomponents.di.MyApp
import kotlin.String

data class SignupModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtLetsGetStarted: String? =
      MyApp.getInstance().resources.getString(R.string.msg_let_s_get_started)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtCreateAnNewAccount: String? =
      MyApp.getInstance().resources.getString(R.string.msg_create_an_new_account)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtConfirmation: String? =
      MyApp.getInstance().resources.getString(R.string.msg_have_an_account)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var etFullNameValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etEmailValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etPasswordValue: String? = null,
  /**
   * TODO Replace with dynamic value
   */
  var etPassword1Value: String? = null
)
