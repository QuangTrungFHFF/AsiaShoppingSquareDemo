package com.asiasquare.byteg.shoppingdemo.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.asiasquare.byteg.shoppingdemo.database.AsiaDatabase
import com.asiasquare.byteg.shoppingdemo.database.items.FavoriteItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavoriteRepository(private val database: AsiaDatabase){

    val favoriteItems: LiveData<List<FavoriteItem>> = database.favoriteItemDao.getAllItemsInFavoriteList()

    suspend fun addFavoriteItem(item: FavoriteItem){
        withContext(Dispatchers.IO){
            database.favoriteItemDao.insert(item)
            Log.d("REPO FAV", "Successful add item to favorite")
        }
    }

}