package com.asiasquare.byteg.shoppingdemo.itemlist

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.asiasquare.byteg.shoppingdemo.R
import com.asiasquare.byteg.shoppingdemo.backendservice.ServerApi
import com.asiasquare.byteg.shoppingdemo.database.items.NetworkItem
import com.asiasquare.byteg.shoppingdemo.datamodel.ItemList
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
        //generateDummyList()
        getData()
    }


//    /**
//     * Create dummy list for testing
//     */
//    private fun generateDummyList(){
//        val itemList = mutableListOf<ItemList>()
//
//        itemList.add(ItemList(0,"Gạo & mì các loại", R.drawable.ct_bungao))
//        itemList.add(ItemList(1,"Thực phẩm đông lạnh", R.drawable.ct_donglanh))
//        itemList.add(ItemList(2,"Gia vị", R.drawable.ct_nuoccham))
//        itemList.add(ItemList(3,"Rau, củ, quả", R.drawable.ct_raucu))
//        itemList.add(ItemList(4,"Đồ khô & ăn vặt", R.drawable.ct_dokho))
//        itemList.add(ItemList(5,"Thực phẩm đóng hộp", R.drawable.ct_dohop))
//
//        _itemList.value= itemList
//    }

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