package com.asiasquare.byteg.shoppingdemo.cart

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.asiasquare.byteg.shoppingdemo.databinding.GirdViewCartItemBinding
import com.asiasquare.byteg.shoppingdemo.datamodel.Cart

class CartFragmentAdapter(private val onClickListener: OnClickListener) : ListAdapter<Cart, CartFragmentAdapter.CartViewHolder>(DiffCallback){

    class CartViewHolder(private val binding: GirdViewCartItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cart: Cart) {
            binding.apply {
                binding.anhItemGioHang.load(cart.imgResource)
                tenItemGioHang.text = cart.textTenSanPham
//                khoiLuongItemGioHang.text = cart.textKhoiLuong
//                giaItemGioHang.text = cart.textGiaSanPham
//                giakhuyenmai.text = cart.textGiaKhuyenMai
            }
        }

//        val imageView2: ImageView = binding.buttonGiam
//        val imageView3: ImageView = binding.buttonTang

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
//        holder.imageView2.setImageResource(R.drawable.minus)
//        holder.imageView3.setImageResource(R.drawable.add)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Cart>(){
        override fun areItemsTheSame(oldItem: Cart, newItem: Cart): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Cart, newItem: Cart): Boolean {
            return oldItem == newItem
        }

    }

    /** Simple ClickListener. Return cart Object info when user click **/
    class OnClickListener(val clickListener : (cart : Cart) -> Unit){
        fun onClick(cart : Cart) = clickListener(cart)
    }


}