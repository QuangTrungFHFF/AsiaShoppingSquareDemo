package com.asiasquare.byteg.shoppingdemo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.asiasquare.byteg.shoppingdemo.database.AsiaDatabase
import com.asiasquare.byteg.shoppingdemo.repository.FavoriteRepository
import kotlinx.coroutines.launch


class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val database = AsiaDatabase.getInstance(application)
    private val favoriteItemRepository = FavoriteRepository(database)

    fun getCountFavorite(): Int {
        var itemCount = 0
        viewModelScope.launch {
            itemCount = favoriteItemRepository.getFavoriteItemCount()
        }
        return itemCount
    }

    /**
     * Factory for constructing CatalogFragmentViewModel with parameter
     */
    class Factory(
        private val app: Application
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                return MainViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }

    }
}