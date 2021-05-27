package com.asiasquare.byteg.shoppingdemo.cart

import android.app.Application
import androidx.lifecycle.*
import com.asiasquare.byteg.shoppingdemo.R
import com.asiasquare.byteg.shoppingdemo.datamodel.Cart

class CartFragmentViewModel(application: Application) : AndroidViewModel(application) {

    /**
     * List of catalog, observe this to get tha change in database
     */
    private val _cartList = MutableLiveData<List<Cart>>()
    val cartList: LiveData<List<Cart>>
        get() = _cartList


    init {
        generateDummyList()
    }

    /**
     * Create dummy list for testing
     */
    private fun generateDummyList() {
        val cartList = mutableListOf<Cart>()

        cartList.add(Cart(0, "Gạo & mì các loại", R.drawable.ct_bungao))
        cartList.add(Cart(1, "Thực phẩm đông lạnh", R.drawable.ct_donglanh))
        cartList.add(Cart(2, "Gia vị", R.drawable.ct_nuoccham))
        cartList.add(Cart(3, "Rau, củ, quả", R.drawable.ct_raucu))
        cartList.add(Cart(4, "Đồ khô & ăn vặt", R.drawable.ct_dokho))
        cartList.add(Cart(5, "Thực phẩm đóng hộp", R.drawable.ct_dohop))

        _cartList.value = cartList
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