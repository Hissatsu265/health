package com.hissatsusapplication.app.modules.chat.`data`.model

import com.hissatsusapplication.app.R
import com.hissatsusapplication.app.appcomponents.di.MyApp
import kotlin.String

data class ChatModel(
  /**
   * TODO Replace with dynamic value
   */
  var txtDrMarcusHorizon: String? =
      MyApp.getInstance().resources.getString(R.string.msg_dr_marcus_horizon)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtConsultionStart: String? =
      MyApp.getInstance().resources.getString(R.string.msg_consultion_start)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtYouCanConsult: String? =
      MyApp.getInstance().resources.getString(R.string.msg_you_can_consult)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDrMarcusHorizon1: String? =
      MyApp.getInstance().resources.getString(R.string.msg_dr_marcus_horizon)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtTime: String? = MyApp.getInstance().resources.getString(R.string.lbl_10_min_ago)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtHelloHowCanI: String? =
      MyApp.getInstance().resources.getString(R.string.msg_hello_how_can_i)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDuration: String? = MyApp.getInstance().resources.getString(R.string.msg_i_have_suffering)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDrMarcusHorizon2: String? =
      MyApp.getInstance().resources.getString(R.string.msg_dr_marcus_horizon)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtTime1: String? = MyApp.getInstance().resources.getString(R.string.lbl_5_min_ago)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtOkDoYouHave: String? = MyApp.getInstance().resources.getString(R.string.msg_ok_do_you_have)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtIDonTHaveAny: String? =
      MyApp.getInstance().resources.getString(R.string.msg_i_don_t_have_any)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtDrMarcusHorizon3: String? =
      MyApp.getInstance().resources.getString(R.string.msg_dr_marcus_horizon)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var txtTime2: String? = MyApp.getInstance().resources.getString(R.string.lbl_online)
  ,
  /**
   * TODO Replace with dynamic value
   */
  var etMessageValue: String? = null
)
