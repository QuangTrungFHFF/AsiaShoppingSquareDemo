package com.asiasquare.byteg.shoppingdemo.detail

import android.app.Application
import androidx.lifecycle.*
import com.asiasquare.byteg.shoppingdemo.database.dao.FavoriteItemDao
import com.asiasquare.byteg.shoppingdemo.database.items.NetworkItem

class DetailFragmentViewModel(
    val database: FavoriteItemDao,
    itemList:NetworkItem,
    application: Application
    ) : AndroidViewModel(application){

    /**
     * List of catalog, observe this to get tha change in database
     */
    private val _selectedItem = MutableLiveData<NetworkItem>()
    val selectedItem : LiveData<NetworkItem>
        get() = _selectedItem

    private val _navigateToPayment = MutableLiveData<NetworkItem?>()
    val navigateToPayment : LiveData<NetworkItem?>
        get() = _navigateToPayment

    init {
        _selectedItem.value = itemList
    }


    fun onPaymentClick( item: NetworkItem){
        _navigateToPayment.value = item
    }

    fun onNavigationComplete(){
        _navigateToPayment.value = null
    }


    class Factory(
        private val dataSource: FavoriteItemDao,
        private val itemProperty: NetworkItem,
        private val app: Application) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(DetailFragmentViewModel::class.java)){
                return DetailFragmentViewModel(dataSource, itemProperty, app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }

    }
}