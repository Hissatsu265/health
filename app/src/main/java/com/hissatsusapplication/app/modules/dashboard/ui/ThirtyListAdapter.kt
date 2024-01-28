package com.hissatsusapplication.app.modules.dashboard.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hissatsusapplication.app.R
import com.hissatsusapplication.app.databinding.RowThirtylistBinding
import com.hissatsusapplication.app.modules.dashboard.`data`.model.ThirtylistRowModel
import kotlin.Int
import kotlin.collections.List

class ThirtyListAdapter(
  var list: List<ThirtylistRowModel>
) : RecyclerView.Adapter<ThirtyListAdapter.RowThirtylistVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowThirtylistVH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_thirtylist,parent,false)
    return RowThirtylistVH(view)
  }

  override fun onBindViewHolder(holder: RowThirtylistVH, position: Int) {
    val thirtylistRowModel = ThirtylistRowModel()
    // TODO uncomment following line after integration with data source
    // val thirtylistRowModel = list[position]
    holder.binding.thirtylistRowModel = thirtylistRowModel
  }

  override fun getItemCount(): Int = 4
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<ThirtylistRowModel>) {
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
      item: ThirtylistRowModel
    ) {
    }
  }

  inner class RowThirtylistVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowThirtylistBinding = RowThirtylistBinding.bind(itemView)
    init {
      binding.btnTicket.setOnClickListener {
        // TODO replace with value from datasource
        clickListener?.onItemClick(it, adapterPosition, ThirtylistRowModel())
      }
    }
  }
}
