package com.asiasquare.byteg.shoppingdemo.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.asiasquare.byteg.shoppingdemo.database.customers.LocalCustomer
import com.asiasquare.byteg.shoppingdemo.database.items.FavoriteItem

@Dao
interface FavoriteItemDao {

    @Insert
    fun insert(item : FavoriteItem)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(item : FavoriteItem)

    @Delete
    fun deleteCustomer(item : FavoriteItem) : Int

    @Query(value = "DELETE FROM favorite_table")
    fun clearFavoriteList()

    @Query(value = "SELECT * FROM favorite_table")
    fun getAllItemsInFavoriteList() : LiveData<List<FavoriteItem>>

    @Query(value = "SELECT * from favorite_table WHERE itemId = :id")
    fun get(id : Int) : FavoriteItem?

    @Query("SELECT * FROM favorite_table ORDER BY itemId DESC LIMIT 1")
    fun getItem(): FavoriteItem?

}