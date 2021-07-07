package com.asiasquare.byteg.shoppingdemo.repository

import androidx.lifecycle.LiveData
import com.asiasquare.byteg.shoppingdemo.database.AsiaDatabase
import com.asiasquare.byteg.shoppingdemo.database.items.FavoriteItem

class FavoriteRepository(private val database: AsiaDatabase){

    val favoriteItems: LiveData<List<FavoriteItem>> = database.favoriteItemDao.getAllItemsInFavoriteList()

}