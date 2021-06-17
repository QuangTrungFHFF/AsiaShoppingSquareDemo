package com.asiasquare.byteg.shoppingdemo.backendservice

import com.asiasquare.byteg.shoppingdemo.database.items.NetworkItem
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

private const val BASE_URL = "http://www.germanyshoppingsquare.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ServerDatabaseApiService{

    @POST("server/ungdungchaua/getsanphamasiaandroid1.php?page=1")
    suspend fun getData(): List<NetworkItem>

}

object ServerApi{
    val retrofitService : ServerDatabaseApiService by lazy {
        retrofit.create(ServerDatabaseApiService::class.java)
    }
}