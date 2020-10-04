package com.example.kotlin.ui.splash

import com.example.kotlin.data.Repository
import com.example.kotlin.data.errors.NoAuthException
import com.example.kotlin.ui.base.BaseViewModel

class SplashViewModel(private val repository: Repository ) : BaseViewModel<Boolean?, SplashViewState>() {

    fun requestUser() {
        repository.getCurrentUser().observeForever {
            viewStateLiveData.value = if (it != null) {
                SplashViewState(isAuth = true)
            } else {
                SplashViewState(error = NoAuthException())
            }
        }
    }
}