package com.hissatsusapplication.app.modules.dashboard.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hissatsusapplication.app.R
import com.hissatsusapplication.app.databinding.RowDoctorlistBinding
import com.hissatsusapplication.app.modules.dashboard.`data`.model.DoctorlistRowModel
import kotlin.Int
import kotlin.collections.List

class DoctorListAdapter(
  var list: List<DoctorlistRowModel>
) : RecyclerView.Adapter<DoctorListAdapter.RowDoctorlistVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowDoctorlistVH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_doctorlist,parent,false)
    return RowDoctorlistVH(view)
  }

  override fun onBindViewHolder(holder: RowDoctorlistVH, position: Int) {
    val doctorlistRowModel = DoctorlistRowModel()
    // TODO uncomment following line after integration with data source
    // val doctorlistRowModel = list[position]
    holder.binding.doctorlistRowModel = doctorlistRowModel
  }

  override fun getItemCount(): Int = 3
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<DoctorlistRowModel>) {
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
      item: DoctorlistRowModel
    ) {
    }
  }

  inner class RowDoctorlistVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowDoctorlistBinding = RowDoctorlistBinding.bind(itemView)
    init {
      binding.linearDoctor.setOnClickListener {
        // TODO replace with value from datasource
        clickListener?.onItemClick(it, adapterPosition, DoctorlistRowModel())
      }
    }
  }
}
