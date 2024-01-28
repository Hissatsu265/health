package com.hissatsusapplication.app.modules.article.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hissatsusapplication.app.R
import com.hissatsusapplication.app.databinding.RowTrendingsBinding
import com.hissatsusapplication.app.modules.article.`data`.model.TrendingsRowModel
import kotlin.Int
import kotlin.collections.List

class TrendingsAdapter(
  var list: List<TrendingsRowModel>
) : RecyclerView.Adapter<TrendingsAdapter.RowTrendingsVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowTrendingsVH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_trendings,parent,false)
    return RowTrendingsVH(view)
  }

  override fun onBindViewHolder(holder: RowTrendingsVH, position: Int) {
    val trendingsRowModel = TrendingsRowModel()
    // TODO uncomment following line after integration with data source
    // val trendingsRowModel = list[position]
    holder.binding.trendingsRowModel = trendingsRowModel
  }

  override fun getItemCount(): Int = 2
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<TrendingsRowModel>) {
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
      item: TrendingsRowModel
    ) {
    }
  }

  inner class RowTrendingsVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowTrendingsBinding = RowTrendingsBinding.bind(itemView)
  }
}
