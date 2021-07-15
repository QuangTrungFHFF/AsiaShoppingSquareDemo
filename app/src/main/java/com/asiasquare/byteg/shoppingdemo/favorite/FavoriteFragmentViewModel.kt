package com.asiasquare.byteg.shoppingdemo.favorite

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.asiasquare.byteg.shoppingdemo.database.AsiaDatabase
import com.asiasquare.byteg.shoppingdemo.database.dao.FavoriteItemDao
import com.asiasquare.byteg.shoppingdemo.database.items.FavoriteItem
import com.asiasquare.byteg.shoppingdemo.database.items.NetworkItem
import com.asiasquare.byteg.shoppingdemo.repository.FavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class
FavoriteFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val database = AsiaDatabase.getInstance(application)
    private val favoriteItemRepository = FavoriteRepository(database)

    val favoriteList = favoriteItemRepository.favoriteItems

    //val items  = favoriteList.getValue()!!.size

    private val tasksEventChannel = Channel<TasksEvent>()
    val tasksEvent = tasksEventChannel.receiveAsFlow()

    fun onDeleteFavoriteClicking(favorite: FavoriteItem) {
        viewModelScope.launch {
            if (favoriteItemRepository.getFavoriteItemById(favorite.itemId) != null) {
                favoriteItemRepository.deleteFavoriteItem(favorite)
                Log.d("Favorite viewmodel", "Item da duoc xoa")
            }
        }

    }




    fun onTaskSwiped(favorite: FavoriteItem) = viewModelScope.launch {
        favoriteItemRepository.deleteFavoriteItem(favorite)
        tasksEventChannel.send(TasksEvent.ShowUndoDeleteTaskMessage(favorite))
    }

    fun onUndoDeleteClick(favorite: FavoriteItem) = viewModelScope.launch {
        favoriteItemRepository.addFavoriteItem(favorite)
    }

    sealed class TasksEvent {

        data class ShowUndoDeleteTaskMessage(val task: FavoriteItem) : TasksEvent()

    }

    /**
     * Factory for constructing CatalogFragmentViewModel with parameter
     */
    class Factory(
        private val app: Application
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FavoriteFragmentViewModel::class.java)) {
                return FavoriteFragmentViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }

    }
}