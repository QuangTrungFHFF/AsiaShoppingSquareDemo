package com.asiasquare.byteg.shoppingdemo.favorite

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.asiasquare.byteg.shoppingdemo.database.items.FavoriteItem
import com.asiasquare.byteg.shoppingdemo.databinding.GridViewFavoriteItemBinding
import com.asiasquare.byteg.shoppingdemo.datamodel.ItemList


class FavoriteFragmentAdapter (private val onClickListener: OnClickListener): ListAdapter<FavoriteItem, FavoriteFragmentAdapter.FavoriteViewHolder>(DiffCallback) {

    /** ViewHolder class **/
    class FavoriteViewHolder(private val binding: GridViewFavoriteItemBinding):RecyclerView.ViewHolder(binding.root) {
        /** Bind item to View, load image here using Coil */
        fun bind (favorite: FavoriteItem){
            binding.anhItemYeuThich.load(favorite.itemImageSource)
            binding.tenItemYeuThich.text = favorite.itemName
        }

        /** inflate the small item in recyclerView **/
        companion object{
            fun from (parent: ViewGroup): FavoriteViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = GridViewFavoriteItemBinding.inflate(layoutInflater,parent,false)
                return FavoriteViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onClickListener.clickListener(item)
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<FavoriteItem>(){
        override fun areItemsTheSame(oldItem: FavoriteItem, newItem: FavoriteItem): Boolean {
            return oldItem.itemId == newItem.itemId
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: FavoriteItem, newItem: FavoriteItem): Boolean {
            return oldItem == newItem
        }


    }

    /** Simple ClickListener. Return favorite Object info when user click **/
    class OnClickListener(val clickListener : (favorite : FavoriteItem) -> Unit){
        fun onClick(favorite: FavoriteItem) = clickListener(favorite)
    }


}