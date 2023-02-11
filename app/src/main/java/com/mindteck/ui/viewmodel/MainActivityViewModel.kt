package com.mindteck.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.launch


class MainActivityViewModel : ViewModel() {

    val  latest_list = MutableLiveData<ArrayList<String>>()
    var  LatestListFlow = latest_list

     fun load(state:Int){
         Log.d("load==>",state.toString())
         var list :ArrayList<String>? = null;
        when(state){
            0->{
                list = bmw()
                viewModelScope.launch { latest_list.postValue(list!!) }

            }
            1->{
                 list = audi()
                viewModelScope.launch { latest_list.postValue(list!!) }


            }

            2->{
                 list = bmw()
                viewModelScope.launch { latest_list.postValue(list!!) }

            }

        }

    }


    fun bmw():ArrayList<String>{
       var  list: ArrayList<String> = ArrayList()
        list.add("BMW 7")
        list.add("BMW X6")
        list.add("BMW X5")
        list.add("BMW X4")
        list.add("BMW X3")
        list.add("BMW X2")
        list.add("BMW X1")
        list.add("BMW X")
        return list;
    }


    fun audi():ArrayList<String>{
        var  list: ArrayList<String> = ArrayList()
        list.add("Audi Q5")
        list.add("Audi Q7")
        list.add("Audi A6")
        list.add("Audi A4")
        list.add("Audi A3")
        list.add("Audi A2")
        list.add("Audi A1")
        list.add("Audi A")
        list.add("Audi M")
        return list;
    }


}