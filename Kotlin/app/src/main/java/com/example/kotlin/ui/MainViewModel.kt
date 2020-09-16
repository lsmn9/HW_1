package com.example.kotlin.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin.data.Repository
<<<<<<< HEAD
import java.util.Collections.copy
=======
>>>>>>> 5a17e6d087ceb108bb43ae6073369653655144e9


class MainViewModel : ViewModel() {

    private val viewStateLiveData: MutableLiveData<MainViewState> = MutableLiveData()

    init {
<<<<<<< HEAD
        Repository.getNotes().observeForever {
            viewStateLiveData.value= viewStateLiveData.value?.
            copy(notes = it!!) ?: MainViewState(it!!)
        }
    }

    fun viewState(): LiveData<MainViewState> = viewStateLiveData
}

=======
        viewStateLiveData.value = MainViewState(Repository.getNotes())
    }

    fun viewState(): LiveData<MainViewState> = viewStateLiveData
}
>>>>>>> 5a17e6d087ceb108bb43ae6073369653655144e9
