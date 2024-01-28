package com.hissatsusapplication.app.modules.chat.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.hissatsusapplication.app.R
import com.hissatsusapplication.app.appcomponents.base.BaseActivity
import com.hissatsusapplication.app.databinding.ActivityChatBinding
import com.hissatsusapplication.app.modules.chat.`data`.viewmodel.ChatVM
import com.hissatsusapplication.app.modules.signup.ui.SignupActivity
import kotlin.String
import kotlin.Unit

class ChatActivity : BaseActivity<ActivityChatBinding>(R.layout.activity_chat) {
  private val viewModel: ChatVM by viewModels<ChatVM>()

  override fun onInitialized(): Unit {
    viewModel.navArguments = intent.extras?.getBundle("bundle")
    binding.chatVM = viewModel
  }

  override fun setUpClicks(): Unit {
    binding.btnSend.setOnClickListener {
      val destIntent = SignupActivity.getIntent(this, null)
      startActivity(destIntent)
    }
    binding.imageArrowLeft.setOnClickListener {
      finish()
    }
  }

  companion object {
    const val TAG: String = "CHAT_ACTIVITY"


    fun getIntent(context: Context, bundle: Bundle?): Intent {
      val destIntent = Intent(context, ChatActivity::class.java)
      destIntent.putExtra("bundle", bundle)
      return destIntent
    }
  }
}
