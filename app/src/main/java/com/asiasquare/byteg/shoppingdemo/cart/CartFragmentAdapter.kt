package com.asiasquare.byteg.shoppingdemo.cart

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.asiasquare.byteg.shoppingdemo.database.items.ShoppingBasketItem
import com.asiasquare.byteg.shoppingdemo.databinding.GirdViewCartItemBinding

class CartFragmentAdapter(private val onClickListener: OnClickListener) : ListAdapter<ShoppingBasketItem, CartFragmentAdapter.CartViewHolder>(DiffCallback){

    class CartViewHolder(private val binding: GirdViewCartItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val btDelete = binding.buttonXoaGioHang
        val btAdd = binding.buttonTang
        val btMinus = binding.buttonGiam

        @SuppressLint("SetTextI18n")
        fun bind(cart: ShoppingBasketItem) {
            binding.apply {
                binding.anhItemGioHang.load(cart.itemImageSource)
                tenItemGioHang.text = cart.itemName
                giaItemGioHang.text = "€" + cart.itemPrice.toString()
                tvItemAmount.text = cart.itemAmount.toString()
            }
        }



        /** inflate the small item in recyclerView **/
        companion object{
            fun from(parent: ViewGroup): CartViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = GirdViewCartItemBinding.inflate(layoutInflater, parent, false)
                return CartViewHolder(binding)
            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder.from(parent)

    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {

        }

        holder.btDelete.setOnClickListener {
            onClickListener.onDeleteClick(item)
        }
        holder.btAdd.setOnClickListener {
            onClickListener.onAddClick(item)
        }
        holder.btMinus.setOnClickListener {
            onClickListener.onMinusClick(item)
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ShoppingBasketItem>(){
        override fun areItemsTheSame(oldItem: ShoppingBasketItem, newItem: ShoppingBasketItem): Boolean {
            return oldItem.itemId == newItem.itemId
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: ShoppingBasketItem, newItem: ShoppingBasketItem): Boolean {
            return oldItem == newItem
        }

    }

    /** Simple ClickListener. Return cart Object info when user click **/
    interface OnClickListener{
        fun onDeleteClick(cart: ShoppingBasketItem)
        fun onAddClick(cart: ShoppingBasketItem)
        fun onMinusClick(cart: ShoppingBasketItem)
    }


}