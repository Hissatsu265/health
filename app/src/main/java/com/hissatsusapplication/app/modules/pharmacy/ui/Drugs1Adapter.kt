package com.hissatsusapplication.app.modules.pharmacy.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hissatsusapplication.app.R
import com.hissatsusapplication.app.databinding.RowDrugs1Binding
import com.hissatsusapplication.app.modules.pharmacy.`data`.model.Drugs1RowModel
import kotlin.Int
import kotlin.collections.List

class Drugs1Adapter(
  var list: List<Drugs1RowModel>
) : RecyclerView.Adapter<Drugs1Adapter.RowDrugs1VH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowDrugs1VH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_drugs1,parent,false)
    return RowDrugs1VH(view)
  }

  override fun onBindViewHolder(holder: RowDrugs1VH, position: Int) {
    val drugs1RowModel = Drugs1RowModel()
    // TODO uncomment following line after integration with data source
    // val drugs1RowModel = list[position]
    holder.binding.drugs1RowModel = drugs1RowModel
  }

  override fun getItemCount(): Int = 3
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<Drugs1RowModel>) {
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
      item: Drugs1RowModel
    ) {
    }
  }

  inner class RowDrugs1VH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowDrugs1Binding = RowDrugs1Binding.bind(itemView)
  }
}
