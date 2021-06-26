package com.asiasquare.byteg.shoppingdemo.itemlist

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.asiasquare.byteg.shoppingdemo.R
import com.asiasquare.byteg.shoppingdemo.backendservice.ServerApi
import com.asiasquare.byteg.shoppingdemo.database.items.NetworkItem
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ItemListFragmentViewModel(application: Application) : AndroidViewModel(application){


    /**
     * List of catalog, observe this to get the change in database
     */

    private val _navigateToDetail = MutableLiveData<NetworkItem?>()
    val navigateToDetail : LiveData<NetworkItem?>
        get() = _navigateToDetail

    private val _text = MutableLiveData<List<NetworkItem>>()
    val text :LiveData<List<NetworkItem>>
        get() = _text


    init {
        getData()
        getDataSecond()
    }



    private fun getData(){
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO){

                    val listResult = ServerApi.retrofitService.getDataFirst()
                    _text.postValue(listResult)
                }
                Log.d("Get data","sucess")
            }catch (e: Exception){
                e.message?.let { Log.d("Get data",it) }
            }
        }
    }

    private fun getDataSecond(){
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO){

                    val listResult = ServerApi.retrofitService.getDataSecond()
                    _text.postValue(listResult)
                }
                Log.d("Get data","sucess")
            }catch (e: Exception){
                e.message?.let { Log.d("Get data",it) }
            }
        }
    }

    fun onDetailClick( itemList: NetworkItem){
        _navigateToDetail.value = itemList
    }

    fun onNavigationComplete(){
        _navigateToDetail.value = null
    }



    class Factory(private val app: Application) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(ItemListFragmentViewModel::class.java)){
                return ItemListFragmentViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }

    }
}