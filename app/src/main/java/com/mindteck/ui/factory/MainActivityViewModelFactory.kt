package com.mindteck.ui.factory


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mindteck.MainActivity
import com.mindteck.ui.viewmodel.MainActivityViewModel

class MainActivityViewModelFactory(activity:MainActivity): ViewModelProvider.NewInstanceFactory() {
    private val context: Context
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return super.create(modelClass)
    }

    init {
        context = activity
    }
}