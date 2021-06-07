package com.asiasquare.byteg.shoppingdemo.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase


abstract class AppDatabase : RoomDatabase(){
//    abstract val sanPhamDao : SanPhamDao
//    abstract val gioHangDao : GioHangDao
//    abstract val yeuthichDao: YeuThichDao
//    abstract val localCustomerDatabaseDao : LocalCustomerDatabaseDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase?=null

        fun getInstance(context: Context) : AppDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "asia_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}