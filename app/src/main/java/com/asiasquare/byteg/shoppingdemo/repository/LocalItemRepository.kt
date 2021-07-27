package com.asiasquare.byteg.shoppingdemo.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.asiasquare.byteg.shoppingdemo.database.AsiaDatabase
import com.asiasquare.byteg.shoppingdemo.database.items.FavoriteItem
import com.asiasquare.byteg.shoppingdemo.database.items.LocalItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalItemRepository(private val database: AsiaDatabase) {

    val localItems: LiveData<List<LocalItem>> = database.itemDao.getAllItems()

    suspend fun addLocalItem(item: LocalItem){
        withContext(Dispatchers.IO){
            database.itemDao.insert(item)
            Log.d("REPO FAV", "Successful add item to local database")
        }
    }

    suspend fun addListLocalItem(items: List<LocalItem>){
        withContext(Dispatchers.IO){
            database.itemDao.insertList(items)
            Log.d("REPO FAV", "Successful add all items to local database")
        }
    }

    suspend fun deleteLocalItem(item: LocalItem){
        withContext(Dispatchers.IO){
            database.itemDao.deleteItem(item)
            Log.d("REPO FAV", "Successful delete item in local database")
        }
    }


}