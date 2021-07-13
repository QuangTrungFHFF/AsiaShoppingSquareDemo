package com.asiasquare.byteg.shoppingdemo.itemlist

import android.app.Application
import android.net.NetworkCapabilities
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.asiasquare.byteg.shoppingdemo.R
import com.asiasquare.byteg.shoppingdemo.backendservice.ServerApi
import com.asiasquare.byteg.shoppingdemo.database.items.NetworkItem
import androidx.navigation.fragment.navArgs
import com.asiasquare.byteg.shoppingdemo.database.AsiaDatabase
import com.asiasquare.byteg.shoppingdemo.repository.FavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

enum class ListStatus { LOADING, ERROR, DONE }

class ItemListFragmentViewModel(item: NetworkItem, application: Application, catalogId: Int) : AndroidViewModel(application){


    /**
     * List of catalog, observe this to get the change in database
     */

    private val _navigateToDetail = MutableLiveData<NetworkItem?>()
    val navigateToDetail : LiveData<NetworkItem?>
        get() = _navigateToDetail

    private val _text = MutableLiveData<List<NetworkItem>>()
    val text :LiveData<List<NetworkItem>>
        get() = _text

    private val _status = MutableLiveData<ListStatus>()
    val status: LiveData<ListStatus>
        get() = _status


    private val database = AsiaDatabase.getInstance(application)
    private val favoriteItemRepository = FavoriteRepository(database)

    private val _selectedItem = item.asDomainItem()


    private val _isFavorite =MutableLiveData<Boolean>()
    val isFavorite : LiveData<Boolean>
        get() = _isFavorite

    init {
        getData(catalogId)
        checkFavorite()
    }



    private fun getData(catalogId: Int){
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO){
                    _status.postValue (ListStatus.LOADING)
                    val listResult = when(catalogId){
                        0-> ServerApi.retrofitService.getDataFirst()
                        1-> ServerApi.retrofitService.getDataSecond()
                        2-> ServerApi.retrofitService.getDataThird()
                        3-> ServerApi.retrofitService.getDataFourth()
                        4-> ServerApi.retrofitService.getDataFifth()
                        5-> ServerApi.retrofitService.getDataSixth()
                        else -> ServerApi.retrofitService.getDataSeventh()
                    }

                    _status.postValue (ListStatus.DONE)
                    _text.postValue(listResult)
                }

                Log.d("Get data $catalogId","sucess")
            }catch (e: Exception){
                _status.postValue (ListStatus.ERROR)

                e.message?.let { Log.d("Get data $catalogId",it) }
            }
        }
    }

    fun onFavoriteClicking() {
        viewModelScope.launch {
            if(isFavorite.value == true){
                Log.d("ItemList viewmodel","Item is added into Favorite")

                favoriteItemRepository.deleteFavoriteItem(_selectedItem.asFavoriteItem())
                _isFavorite.value = false

            }else
            {
                favoriteItemRepository.addFavoriteItem(_selectedItem.asFavoriteItem())

                _isFavorite.value = true
            }

        }
    }

    fun checkFavorite() {
        viewModelScope.launch {
            _isFavorite.value =
                favoriteItemRepository.getFavoriteItemById(_selectedItem.itemId) !== null

        }
    }

    fun onDetailClick( itemList: NetworkItem){
        _navigateToDetail.value = itemList
    }

    fun onNavigationComplete(){
        _navigateToDetail.value = null
    }


    class Factory(private val item: NetworkItem,
                  private val app: Application, private val catalogId: Int) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(ItemListFragmentViewModel::class.java)){
                return ItemListFragmentViewModel(item, app,catalogId) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }

    }
}