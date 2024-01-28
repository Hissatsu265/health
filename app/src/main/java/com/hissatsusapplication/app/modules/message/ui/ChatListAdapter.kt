package com.hissatsusapplication.app.modules.message.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hissatsusapplication.app.R
import com.hissatsusapplication.app.databinding.RowChatlistBinding
import com.hissatsusapplication.app.modules.message.`data`.model.ChatlistRowModel
import kotlin.Int
import kotlin.collections.List

class ChatListAdapter(
  var list: List<ChatlistRowModel>
) : RecyclerView.Adapter<ChatListAdapter.RowChatlistVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowChatlistVH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_chatlist,parent,false)
    return RowChatlistVH(view)
  }

  override fun onBindViewHolder(holder: RowChatlistVH, position: Int) {
    val chatlistRowModel = ChatlistRowModel()
    // TODO uncomment following line after integration with data source
    // val chatlistRowModel = list[position]
    holder.binding.chatlistRowModel = chatlistRowModel
  }

  override fun getItemCount(): Int = 3
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<ChatlistRowModel>) {
    list = newData
    notifyDataSetChanged()
  }

  fun setOnItemClickListener(clickListener: OnItemClickListener) {
    this.clickListener = clickListener
  }

  interface OnItemClickListener {
    fun onItemClick(
      view: View,
      position: Int,
      item: ChatlistRowModel
    ) {
    }
  }

  inner class RowChatlistVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowChatlistBinding = RowChatlistBinding.bind(itemView)
    init {
      binding.linearChat.setOnClickListener {
        // TODO replace with value from datasource
        clickListener?.onItemClick(it, adapterPosition, ChatlistRowModel())
      }
    }
  }
}
