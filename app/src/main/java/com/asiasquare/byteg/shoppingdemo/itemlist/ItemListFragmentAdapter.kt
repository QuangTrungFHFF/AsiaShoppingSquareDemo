package com.asiasquare.byteg.shoppingdemo.itemlist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.asiasquare.byteg.shoppingdemo.R
import com.asiasquare.byteg.shoppingdemo.database.items.NetworkItem
import com.asiasquare.byteg.shoppingdemo.databinding.GridViewItemListBinding



class ItemListFragmentAdapter(private val onClickListener: OnClickListener):ListAdapter <NetworkItem, ItemListFragmentAdapter.ItemListViewHolder>(DiffCallback) {


    class ItemListViewHolder (private val binding: GridViewItemListBinding):
        RecyclerView.ViewHolder(binding.root)  {

        val btnFavorite= binding.imageViewTim
        fun bind(item: NetworkItem) {
            binding.apply {
                anhsanpham.load(item.itemImageSource){
                    placeholder(R.drawable.loading_animation)
                    error(R.drawable.ic_connection_error)
                }
                tensanpham.text = item.itemName
                giasanpham.text= item.itemPrice.toString()
            }

        }
        companion object{
            fun from(parent: ViewGroup): ItemListViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = GridViewItemListBinding
                    .inflate(layoutInflater, parent, false)
                return ItemListViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): ItemListViewHolder {
        return ItemListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ItemListViewHolder,position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onClickListener.onItemClick(item)
        }

        holder.btnFavorite.setOnClickListener {
            onClickListener.onAddFavoriteClick(item)
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<NetworkItem>(){
        override fun areItemsTheSame(oldItem: NetworkItem, newItem: NetworkItem): Boolean {
            return oldItem.itemId == newItem.itemId
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: NetworkItem, newItem: NetworkItem): Boolean {
            return oldItem == newItem
        }

    }

    /** Simple ClickListener. Return itemList Object info when user click **/

    interface OnClickListener{
        fun onItemClick(item: NetworkItem)
        fun onAddFavoriteClick(item: NetworkItem)
    }

}

