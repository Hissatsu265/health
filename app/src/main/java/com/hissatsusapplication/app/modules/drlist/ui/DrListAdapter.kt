package com.hissatsusapplication.app.modules.drlist.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hissatsusapplication.app.R
import com.hissatsusapplication.app.databinding.RowDrlistBinding
import com.hissatsusapplication.app.modules.drlist.`data`.model.DrlistRowModel
import kotlin.Int
import kotlin.collections.List

class DrListAdapter(
  var list: List<DrlistRowModel>
) : RecyclerView.Adapter<DrListAdapter.RowDrlistVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowDrlistVH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_drlist,parent,false)
    return RowDrlistVH(view)
  }

  override fun onBindViewHolder(holder: RowDrlistVH, position: Int) {
    val drlistRowModel = DrlistRowModel()
    // TODO uncomment following line after integration with data source
    // val drlistRowModel = list[position]
    holder.binding.drlistRowModel = drlistRowModel
  }

  override fun getItemCount(): Int = 4
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<DrlistRowModel>) {
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
      item: DrlistRowModel
    ) {
    }
  }

  inner class RowDrlistVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowDrlistBinding = RowDrlistBinding.bind(itemView)
  }
}
