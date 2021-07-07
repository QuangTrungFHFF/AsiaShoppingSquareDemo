package com.asiasquare.byteg.shoppingdemo.favorite

import android.app.Application
import androidx.lifecycle.*
import com.asiasquare.byteg.shoppingdemo.R
import com.asiasquare.byteg.shoppingdemo.database.AsiaDatabase
import com.asiasquare.byteg.shoppingdemo.database.items.FavoriteItem
import com.asiasquare.byteg.shoppingdemo.datamodel.ItemList
import com.asiasquare.byteg.shoppingdemo.repository.FavoriteRepository

class
FavoriteFragmentViewModel (application: Application) : AndroidViewModel(application) {

    private val database = AsiaDatabase.getInstance(application)
    private val favoriteItemRepository = FavoriteRepository(database)

    val favoriteList = favoriteItemRepository.favoriteItems



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