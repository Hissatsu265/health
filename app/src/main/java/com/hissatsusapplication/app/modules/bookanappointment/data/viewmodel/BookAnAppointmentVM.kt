package com.hissatsusapplication.app.modules.bookanappointment.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hissatsusapplication.app.modules.bookanappointment.`data`.model.BookAnAppointmentModel
import org.koin.core.KoinComponent

class BookAnAppointmentVM : ViewModel(), KoinComponent {
  val bookAnAppointmentModel: MutableLiveData<BookAnAppointmentModel> =
      MutableLiveData(BookAnAppointmentModel())

  var navArguments: Bundle? = null
}
