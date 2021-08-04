package com.asiasquare.byteg.shoppingdemo.cart

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.asiasquare.byteg.shoppingdemo.R
import com.asiasquare.byteg.shoppingdemo.database.AsiaDatabase
import com.asiasquare.byteg.shoppingdemo.database.items.FavoriteItem
import com.asiasquare.byteg.shoppingdemo.database.items.ShoppingBasketItem
import com.asiasquare.byteg.shoppingdemo.datamodel.ItemList
import com.asiasquare.byteg.shoppingdemo.repository.CartRepository
import com.asiasquare.byteg.shoppingdemo.repository.FavoriteRepository
import kotlinx.coroutines.launch

class CartFragmentViewModel(application: Application) : AndroidViewModel(application) {

    /**
     * List of catalog, observe this to get tha change in database
     */


    private val database = AsiaDatabase.getInstance(application)
    private val cartRepository = CartRepository(database)

    val cartList = cartRepository.cartItems

    init {
        //generateDummyList()
    }

    /**
     * Create dummy list for testing
     */
//    private fun generateDummyList() {
//        val cartList = mutableListOf<ItemList>()
//
//        cartList.add(ItemList(0, "Gạo & mì các loại", R.drawable.ct_bungao))
//        cartList.add(ItemList(1, "Thực phẩm đông lạnh", R.drawable.ct_donglanh))
//        cartList.add(ItemList(2, "Gia vị", R.drawable.ct_nuoccham))
//        cartList.add(ItemList(3, "Rau, củ, quả", R.drawable.ct_raucu))
//        cartList.add(ItemList(4, "Đồ khô & ăn vặt", R.drawable.ct_dokho))
//        cartList.add(ItemList(5, "Thực phẩm đóng hộp", R.drawable.ct_dohop))
//
//        _cartList.value = cartList
//    }

    fun onDeleteCartClicking(cart: ShoppingBasketItem) {
        viewModelScope.launch {
            if (cartRepository.getCartItemById(cart.itemId) != null) {
                cartRepository.deleteCartItem(cart)
                Log.d("Favorite viewmodel", "Item da duoc xoa")
            }
        }
    }


    /**
     * Factory for constructing CartViewModel with parameter
     */
    class Factory(private val app: Application) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(CartFragmentViewModel::class.java)){
                return CartFragmentViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }

    }
}