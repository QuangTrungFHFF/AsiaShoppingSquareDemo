package com.asiasquare.byteg.shoppingdemo.detail

import android.app.Application
import androidx.core.content.res.FontResourcesParserCompat
import androidx.lifecycle.*
import com.asiasquare.byteg.shoppingdemo.database.dao.FavoriteItemDao
import com.asiasquare.byteg.shoppingdemo.database.items.FavoriteItem
import com.asiasquare.byteg.shoppingdemo.database.items.NetworkItem
import kotlinx.coroutines.*

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



    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private var thisFavoriteItem = MutableLiveData<FavoriteItem?>()


    private val items = database.getAllItemsInFavoriteList()
//    val nightsString = Transformations.map(items) { items ->
//        formatNights(items, application.resources)
//    }


    private val uiScope = CoroutineScope(Dispatchers.Main +  viewModelJob)

    init {
        _selectedItem.value = itemList
        initializeFavoriteList()
    }


    private fun initializeFavoriteList() {
        uiScope.launch {
            thisFavoriteItem.value = getThisItemFromDatabase()
        }
    }
    private suspend fun getThisItemFromDatabase():  FavoriteItem? {
        return withContext(Dispatchers.IO) {
            var thisItem = database.getItem()
            thisItem
        }
    }

    fun onLikeImageClicking() {
        uiScope.launch {
            val newFavoriteItem = FavoriteItem()
            insert(newFavoriteItem)
            thisFavoriteItem.value = getThisItemFromDatabase()
        }
    }

    private suspend fun insert(favoriteItem: FavoriteItem) {
        withContext(Dispatchers.IO) {
            database.insert(favoriteItem)
        }
    }

    fun onClear() {
        uiScope.launch {
            clear()
            thisFavoriteItem.value=null
        }
    }

    private suspend fun clear(){
        withContext(Dispatchers.IO){
            database.clearFavoriteList()
        }
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