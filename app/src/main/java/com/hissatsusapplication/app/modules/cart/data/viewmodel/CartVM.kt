package com.hissatsusapplication.app.modules.cart.`data`.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hissatsusapplication.app.modules.cart.`data`.model.CartModel
import com.hissatsusapplication.app.modules.cart.`data`.model.DrugslistRowModel
import kotlin.collections.MutableList
import org.koin.core.KoinComponent

class CartVM : ViewModel(), KoinComponent {
  val cartModel: MutableLiveData<CartModel> = MutableLiveData(CartModel())

  var navArguments: Bundle? = null

  val drugsListList: MutableLiveData<MutableList<DrugslistRowModel>> =
      MutableLiveData(mutableListOf())
}
