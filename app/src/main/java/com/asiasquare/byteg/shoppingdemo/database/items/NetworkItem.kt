package com.asiasquare.byteg.shoppingdemo.database.items

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

/**Domain Model for Item**/
@Parcelize
data class NetworkItem(
    @Json(name = "id")
    val itemId : Int,
    @Json(name = "idHang")
    val itemBrandId: Int,
    @Json(name = "tenSanPham")
    val itemName: String,
    @Json(name = "giaSanPham")
    val itemPrice : Double,
    @Json(name = "hinhAnhSanPham")
    val itemImageSource : String,
    @Json(name = "khoiLuong")
    val itemWeight : String,
    @Json(name = "moTa")
    val itemDescription : String,
    @Json(name = "thuongHieu")
    val itemBrand : String,
    @Json(name = "xuatXu")
    val itemOrigin : String
) : Parcelable {
    fun asLocalItem() : LocalItem {
        return LocalItem(
            itemId = itemId,
            itemName = itemName,
            itemPrice = itemPrice,
            itemDiscountedPrice = 0.00,
            itemImageSource = itemImageSource,
            itemWeight = itemWeight,
            itemDescription = itemDescription,
            itemBrand = itemBrand,
            itemOrigin = itemOrigin
        )
    }
    fun asDomainItem() : Item {
        return Item(
            itemId = itemId,
            itemName = itemName,
            itemPrice = itemPrice,
            itemDiscountedPrice = 0.00,
            itemImageSource = itemImageSource,
            itemWeight = itemWeight,
            itemDescription = itemDescription,
            itemBrand = itemBrand,
            itemOrigin = itemOrigin
        )
    }
}