package com.asiasquare.byteg.shoppingdemo.database.items

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_table")
data class FavoriteItem (
    @PrimaryKey (autoGenerate = true)
    var itemId : Int = 0,
    @ColumnInfo(name = "item_name")
    var itemName: String = "",
    @ColumnInfo(name = "item_price")
    var itemPrice : Double= 0.0,
    @ColumnInfo(name = "item_discounted_price")
    var itemDiscountedPrice : Double= 0.0,
    @ColumnInfo(name = "item_image_src")
    var itemImageSource : String= "",
    @ColumnInfo(name = "item_weight")
    var itemWeight : String= "",
    @ColumnInfo(name = "item_description")
    var itemDescription : String= "",
    @ColumnInfo(name = "item_brand")
    var itemBrand : String= "",
    @ColumnInfo(name = "item_origin")
    var itemOrigin : String= "",
    @ColumnInfo(name = "item_amount")
    var itemAmount : Int= 0
)