package com.asiasquare.byteg.shoppingdemo.itemlist

import android.app.Application
import androidx.lifecycle.*
import com.asiasquare.byteg.shoppingdemo.ListItemApi
import com.asiasquare.byteg.shoppingdemo.R
import com.asiasquare.byteg.shoppingdemo.database.items.NetworkItem
import com.asiasquare.byteg.shoppingdemo.datamodel.ItemList
import kotlinx.coroutines.launch

class ItemListFragmentViewModel(application: Application) : AndroidViewModel(application){

    enum class ListItemApiStatus { LOADING, ERROR, DONE }

    private val _status = MutableLiveData<ListItemApiStatus>()
    val status: LiveData<ListItemApiStatus>
        get() = _status


    /**
     * List of catalog, observe this to get tha change in database
     */
    private val _itemList = MutableLiveData<List<NetworkItem>>()
    val itemList : LiveData<List<NetworkItem>>
        get() = _itemList

    private val _navigateToDetail = MutableLiveData<NetworkItem?>()
    val navigateToDetail : LiveData<NetworkItem?>
        get() = _navigateToDetail

    init {
        //generateDummyList()
        getItemList()
    }


    /**
     * Create dummy list for testing
     */
//    private fun generateDummyList(){
//        val itemList = mutableListOf<NetworkItem>()
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

    private fun getItemList() {

        viewModelScope.launch {
            _status.value = ListItemApiStatus.LOADING

            try {
                _itemList.value = ListItemApi.retrofitService.getProperties()
                _status.value = ListItemApiStatus.DONE


            } catch (e: Exception) {
                _status.value = ListItemApiStatus.ERROR
                _itemList.value = ArrayList()
            }
        }

    }


    fun onDetailClick( networkItem: NetworkItem){
        _navigateToDetail.value = networkItem
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