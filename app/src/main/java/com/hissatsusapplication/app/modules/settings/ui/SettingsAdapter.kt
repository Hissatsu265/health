package com.hissatsusapplication.app.modules.settings.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hissatsusapplication.app.R
import com.hissatsusapplication.app.databinding.RowSettingsBinding
import com.hissatsusapplication.app.modules.settings.`data`.model.SettingsRowModel
import kotlin.Int
import kotlin.collections.List

class SettingsAdapter(
  var list: List<SettingsRowModel>
) : RecyclerView.Adapter<SettingsAdapter.RowSettingsVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowSettingsVH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_settings,parent,false)
    return RowSettingsVH(view)
  }

  override fun onBindViewHolder(holder: RowSettingsVH, position: Int) {
    val settingsRowModel = SettingsRowModel()
    // TODO uncomment following line after integration with data source
    // val settingsRowModel = list[position]
    holder.binding.settingsRowModel = settingsRowModel
  }

  override fun getItemCount(): Int = 3
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<SettingsRowModel>) {
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
      item: SettingsRowModel
    ) {
    }
  }

  inner class RowSettingsVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowSettingsBinding = RowSettingsBinding.bind(itemView)
  }
}
