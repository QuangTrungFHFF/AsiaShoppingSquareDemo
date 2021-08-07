package com.asiasquare.byteg.shoppingdemo.detail

import android.R
import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.asiasquare.byteg.shoppingdemo.database.AsiaDatabase
import com.asiasquare.byteg.shoppingdemo.database.items.LocalItem
import com.asiasquare.byteg.shoppingdemo.database.items.NetworkItem
import com.asiasquare.byteg.shoppingdemo.database.items.ShoppingBasketItem
import com.asiasquare.byteg.shoppingdemo.repository.CartRepository
import com.asiasquare.byteg.shoppingdemo.repository.FavoriteRepository
import kotlinx.coroutines.*

class DetailFragmentViewModel(item: LocalItem, application: Application) : AndroidViewModel(application){

    private val database = AsiaDatabase.getInstance(application)
    private val favoriteItemRepository = FavoriteRepository(database)
    private val cartItemRepository = CartRepository(database)


    private val _selectedItem = item.asDomainItem()

    private val _isFavorite =MutableLiveData<Boolean>()
    val isFavorite : LiveData<Boolean>
        get() = _isFavorite



    init {
        checkFavorite()
    }

    fun onFavoriteClicking() {
        viewModelScope.launch {
            if(isFavorite.value == true){
                Log.d("Detail viewmodel","Item da co trong favorite")
                favoriteItemRepository.deleteFavoriteItem(_selectedItem.asFavoriteItem())
                _isFavorite.value = false

            }else{
                favoriteItemRepository.addFavoriteItem(_selectedItem.asFavoriteItem())
                _isFavorite.value = true
            }
        }
    }

    private fun checkFavorite() {
        viewModelScope.launch {
            _isFavorite.value =
                favoriteItemRepository.getFavoriteItemById(_selectedItem.itemId) !== null
        }
    }


    fun onCartClicking() {
        viewModelScope.launch {
            if (cartItemRepository.getCartItemById(_selectedItem.itemId) != null) {
                cartItemRepository.updateCartItem(_selectedItem.asCartItem())
                Log.d("Detail viewmodel", "So Luong da duoc update")
            } else

                cartItemRepository.addCartItem(_selectedItem.asCartItem())
            Log.d("Detail viewmodel","Them Item vao Shopping Basket")
            }
        }




    class Factory(
        private val item: LocalItem,
                private val app: Application) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(DetailFragmentViewModel::class.java)){
                return DetailFragmentViewModel( item, app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }

    }
}