package com.hissatsusapplication.app.modules.pharmacy.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hissatsusapplication.app.R
import com.hissatsusapplication.app.databinding.RowDrugsBinding
import com.hissatsusapplication.app.modules.pharmacy.`data`.model.DrugsRowModel
import kotlin.Int
import kotlin.collections.List

class DrugsAdapter(
  var list: List<DrugsRowModel>
) : RecyclerView.Adapter<DrugsAdapter.RowDrugsVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowDrugsVH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_drugs,parent,false)
    return RowDrugsVH(view)
  }

  override fun onBindViewHolder(holder: RowDrugsVH, position: Int) {
    val drugsRowModel = DrugsRowModel()
    // TODO uncomment following line after integration with data source
    // val drugsRowModel = list[position]
    holder.binding.drugsRowModel = drugsRowModel
  }

  override fun getItemCount(): Int = 3
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<DrugsRowModel>) {
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
      item: DrugsRowModel
    ) {
    }
  }

  inner class RowDrugsVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowDrugsBinding = RowDrugsBinding.bind(itemView)
  }
}
