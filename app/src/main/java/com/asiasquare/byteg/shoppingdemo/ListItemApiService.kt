package com.asiasquare.byteg.shoppingdemo

import com.asiasquare.byteg.shoppingdemo.database.items.NetworkItem
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

//test link
private const val BASE_URL = "http://www.germanyshoppingsquare.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
    .baseUrl(BASE_URL)
    .build()

//test link
interface ListItemApiService{
    @GET("server/ungdungchaua/getsanphamasiaandroid0.php/{idHang}")
    //suspend fun getProperties(): List<NetworkItem>
    fun getProperties(): Call<List<NetworkItem>>

}


object ListItemApi{
    val retrofitService : ListItemApiService by lazy {
        retrofit.create(ListItemApiService::class.java)
    }
}

