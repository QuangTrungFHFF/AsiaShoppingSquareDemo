package com.asiasquare.byteg.shoppingdemo.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.asiasquare.byteg.shoppingdemo.database.AsiaDatabase
import com.asiasquare.byteg.shoppingdemo.database.items.ShoppingBasketItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CartRepository(private val database: AsiaDatabase){

    val cartItems: LiveData<List<ShoppingBasketItem>> = database.basketItemDao.getAllItemsInBasket()

    val cartLiveItemCount: LiveData<Int> = database.basketItemDao.getLiveCartItemsCount()

    suspend fun addCartItem(item: ShoppingBasketItem){
        withContext(Dispatchers.IO){
            database.basketItemDao.insert(item)
            Log.d("REPO FAV", "Successful add item to Shopping cart")
        }
    }

    suspend fun updateCartItem(item: ShoppingBasketItem){
        withContext(Dispatchers.IO){
            database.basketItemDao.update(item)
            Log.d("REPO FAV", "Successful update item to Shopping cart")
        }
    }

    suspend fun deleteCartItem(item: ShoppingBasketItem){
        withContext(Dispatchers.IO){
            database.basketItemDao.deleteItem(item)
            Log.d("REPO FAV", "Successful delete item in Shopping cart")
        }
    }

    suspend fun getCartItemById(id: Int) : ShoppingBasketItem? {
        var item : ShoppingBasketItem? = null
        withContext(Dispatchers.IO){
            item = database.basketItemDao.get(id)
        }
        return item
    }


}