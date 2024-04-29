package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.ui.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication.core.utils.DynamicColor
import com.example.myapplication.databinding.ItemLoadingBinding
import com.example.myapplication.databinding.ItemRowBinding
import com.example.myapplication.domain.model.Posts


class RecyclerViewAdapter(var mItemList: ArrayList<Posts?>): RecyclerView.Adapter<ViewHolder>() {

    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1
    var onItemClick: ((Posts) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == VIEW_TYPE_ITEM) {
            val rowBinding = ItemRowBinding.inflate(LayoutInflater.from(parent.context),
                parent , false)
            ItemViewHolder(rowBinding)
        } else {
            val itemLoadingBinding = ItemLoadingBinding.inflate(LayoutInflater.from(parent.context),
                parent , false)
            LoadingViewHolder(itemLoadingBinding)
        }
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        if (viewHolder is ItemViewHolder) {
            populateItemRows(viewHolder, position)
        } else if (viewHolder is LoadingViewHolder) {
            showLoadingView(viewHolder, position)
        }
    }

    override fun getItemCount(): Int {
        return if (mItemList == null) 0 else mItemList.size
    }

    /**
     * The following method decides the type of ViewHolder to display in the RecyclerView
     *
     * @param position
     * @return
     */
    override fun getItemViewType(position: Int): Int {
        return if (mItemList[position] == null) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    }

    private inner class ItemViewHolder(binding: ItemRowBinding) : ViewHolder(binding.root)

    private inner class LoadingViewHolder(binding: ItemLoadingBinding) : ViewHolder(binding.root)

    private fun showLoadingView(viewHolder: LoadingViewHolder, position: Int) {
        //ProgressBar would be displayed
    }

    private fun populateItemRows(viewHolder: ItemViewHolder, position: Int) {
        val item = mItemList[position]
        viewHolder.itemView.findViewById<TextView>(R.id.tvUserId).text = "UserId - "+item?.userId.toString()
        viewHolder.itemView.findViewById<TextView>(R.id.tvTitle).text = item?.title
        viewHolder.itemView.findViewById<TextView>(R.id.tvId).text = item?.id.toString()
    }
}