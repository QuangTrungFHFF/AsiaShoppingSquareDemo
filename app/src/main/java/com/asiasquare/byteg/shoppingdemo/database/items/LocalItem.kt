package com.asiasquare.byteg.shoppingdemo.database.items

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.asiasquare.byteg.shoppingdemo.database.customers.Address
import com.asiasquare.byteg.shoppingdemo.database.customers.LocalAddress
import kotlinx.parcelize.Parcelize

@Entity(tableName = "local_items_table")
data class LocalItem(
    @PrimaryKey(autoGenerate = true)
    var localItemId: Int,
    @ColumnInfo(name = "item_id")
    var itemId : Int,
    @ColumnInfo(name = "item_name")
    var itemName: String,
    @ColumnInfo(name = "item_price")
    var itemPrice : Double,
    @ColumnInfo(name = "item_sale_price")
    var email : String
)