package com.asiasquare.byteg.shoppingdemo.favorite

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.asiasquare.byteg.shoppingdemo.database.AsiaDatabase
import com.asiasquare.byteg.shoppingdemo.database.dao.FavoriteItemDao
import com.asiasquare.byteg.shoppingdemo.database.items.FavoriteItem
import com.asiasquare.byteg.shoppingdemo.database.items.NetworkItem
import com.asiasquare.byteg.shoppingdemo.repository.FavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class
FavoriteFragmentViewModel (application: Application) : AndroidViewModel(application) {

    private val database = AsiaDatabase.getInstance(application)
    private val favoriteItemRepository = FavoriteRepository(database)

    val favoriteList = favoriteItemRepository.favoriteItems

   private val _selectedItems =FavoriteItem()



    fun onDeleteFavoriteClicking() {
        viewModelScope.launch {
            if(favoriteItemRepository.getFavoriteItemById(_selectedItems.itemId)!= null){
                Log.d("Favorite viewmodel","Item da duoc xoa")

                favoriteItemRepository.deleteFavoriteItem(_selectedItems)

            }
            }

        }



    /**
     * Factory for constructing CatalogFragmentViewModel with parameter
     */
    class Factory(private val app: Application) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(FavoriteFragmentViewModel::class.java)){
                return FavoriteFragmentViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }

    }
}