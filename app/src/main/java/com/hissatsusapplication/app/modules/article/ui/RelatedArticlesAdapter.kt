package com.hissatsusapplication.app.modules.article.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hissatsusapplication.app.R
import com.hissatsusapplication.app.databinding.RowRelatedarticlesBinding
import com.hissatsusapplication.app.modules.article.`data`.model.RelatedarticlesRowModel
import kotlin.Int
import kotlin.collections.List

class RelatedArticlesAdapter(
  var list: List<RelatedarticlesRowModel>
) : RecyclerView.Adapter<RelatedArticlesAdapter.RowRelatedarticlesVH>() {
  private var clickListener: OnItemClickListener? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowRelatedarticlesVH {
    val view=LayoutInflater.from(parent.context).inflate(R.layout.row_relatedarticles,parent,false)
    return RowRelatedarticlesVH(view)
  }

  override fun onBindViewHolder(holder: RowRelatedarticlesVH, position: Int) {
    val relatedarticlesRowModel = RelatedarticlesRowModel()
    // TODO uncomment following line after integration with data source
    // val relatedarticlesRowModel = list[position]
    holder.binding.relatedarticlesRowModel = relatedarticlesRowModel
  }

  override fun getItemCount(): Int = 2
  // TODO uncomment following line after integration with data source
  // return list.size

  public fun updateData(newData: List<RelatedarticlesRowModel>) {
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
      item: RelatedarticlesRowModel
    ) {
    }
  }

  inner class RowRelatedarticlesVH(
    view: View
  ) : RecyclerView.ViewHolder(view) {
    val binding: RowRelatedarticlesBinding = RowRelatedarticlesBinding.bind(itemView)
  }
}
