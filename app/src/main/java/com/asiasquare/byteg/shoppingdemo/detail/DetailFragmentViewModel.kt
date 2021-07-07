package com.asiasquare.byteg.shoppingdemo.detail

import android.app.Application
import androidx.core.content.res.FontResourcesParserCompat
import androidx.lifecycle.*
import com.asiasquare.byteg.shoppingdemo.database.AsiaDatabase
import com.asiasquare.byteg.shoppingdemo.database.dao.FavoriteItemDao
import com.asiasquare.byteg.shoppingdemo.database.items.FavoriteItem
import com.asiasquare.byteg.shoppingdemo.database.items.NetworkItem
import com.asiasquare.byteg.shoppingdemo.repository.FavoriteRepository
import kotlinx.coroutines.*

class DetailFragmentViewModel(item:NetworkItem, application: Application) : AndroidViewModel(application){

    private val database = AsiaDatabase.getInstance(application)
    private val favoriteItemRepository = FavoriteRepository(database)

    /**
     * List of catalog, observe this to get tha change in database
     */
    private val _selectedItem = item


    init {

    }


    private fun initializeFavoriteList() {

    }


    fun onAddFavoriteClicking() {


    }




    class Factory(
        private val item: NetworkItem,
        private val app: Application) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(DetailFragmentViewModel::class.java)){
                return DetailFragmentViewModel( item, app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }

    }
}