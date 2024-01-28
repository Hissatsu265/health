package com.hissatsusapplication.app.modules.cart.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hissatsusapplication.app.R
import com.hissatsusapplication.app.databinding.RowDrugslistBinding
import com.hissatsusapplication.app.modules.cart.`data`.model.DrugslistRowModel
import kotlin.Int
import kotlin.collections.List

class DrugsListAdapter(
  var list: List<DrugslistRowModel>
) : RecyclerView.Adapter<DrugsListAdapter.RowDrugslistVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowDrugslistVH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_drugslist,parent,false)
    return RowDrugslistVH(view)
  }

  override fun onBindViewHolder(holder: RowDrugslistVH, position: Int) {
    val drugslistRowModel = DrugslistRowModel()
    // TODO uncomment following line after integration with data source
    // val drugslistRowModel = list[position]
    holder.binding.drugslistRowModel = drugslistRowModel
  }

  override fun getItemCount(): Int = 2
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<DrugslistRowModel>) {
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
      item: DrugslistRowModel
    ) {
    }
  }

  inner class RowDrugslistVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowDrugslistBinding = RowDrugslistBinding.bind(itemView)
  }
}
