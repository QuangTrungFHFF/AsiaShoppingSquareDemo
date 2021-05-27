package com.asiasquare.byteg.shoppingdemo.favorite

import android.app.Application
import androidx.lifecycle.*
import com.asiasquare.byteg.shoppingdemo.R
import com.asiasquare.byteg.shoppingdemo.datamodel.Catalog
import com.asiasquare.byteg.shoppingdemo.datamodel.Favorite

class FavoriteFragmentViewModel (application: Application) : AndroidViewModel(application) {
    private val _favoriteList = MutableLiveData<List<Favorite>>()
    val favoriteList : LiveData<List<Favorite>>
        get() = _favoriteList

    init {
        generateDummyList()
    }


    /**
     * Create dummy list for testing
     */
    private fun generateDummyList(){
        val favoriteList = mutableListOf<Favorite>()

        favoriteList.add(Favorite(0,"Gạo & mì các loại", R.drawable.ct_bungao))
        favoriteList.add(Favorite(1,"Thực phẩm đông lạnh", R.drawable.ct_donglanh))
        favoriteList.add(Favorite(2,"Gia vị", R.drawable.ct_nuoccham))
        favoriteList.add(Favorite(3,"Rau, củ, quả", R.drawable.ct_raucu))
        favoriteList.add(Favorite(4,"Đồ khô & ăn vặt", R.drawable.ct_dokho))
        favoriteList.add(Favorite(5,"Thực phẩm đóng hộp", R.drawable.ct_dohop))

        _favoriteList.value= favoriteList
    }


    /**
     * Factory for constructing CatalogFragmentViewModel with parameter
     */
    class Factory(private val app: Application) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(FavoriteFragmentViewModel::class.java)){
                return FavoriteFragmentViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }

    }
}