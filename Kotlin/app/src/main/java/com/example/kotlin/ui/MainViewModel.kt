package com.example.kotlin.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin.data.Repository
import java.util.Collections.copy


class MainViewModel : ViewModel() {

    private val viewStateLiveData: MutableLiveData<MainViewState> = MutableLiveData()

    init {
        Repository.getNotes().observeForever {
            viewStateLiveData.value= viewStateLiveData.value?.
            copy(notes = it!!) ?: MainViewState(it!!)
        }
    }

    fun viewState(): LiveData<MainViewState> = viewStateLiveData
}

