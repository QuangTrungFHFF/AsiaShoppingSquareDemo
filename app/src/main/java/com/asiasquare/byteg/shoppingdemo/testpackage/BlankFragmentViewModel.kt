package com.asiasquare.byteg.shoppingdemo.testpackage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BlankFragmentViewModel(application: Application):AndroidViewModel(application){







    
    /**
     * Factory for constructing InfoViewModel with parameter
     */
    class Factory(val app: Application) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(BlankFragmentViewModel::class.java)){
                return BlankFragmentViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }

    }
}