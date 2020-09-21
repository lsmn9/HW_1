package com.example.kotlin.ui.splash

import com.example.kotlin.ui.base.BaseViewState

class SplashViewState(isAuth: Boolean? = null, error: Throwable? = null) :
        BaseViewState<Boolean?>(isAuth, error)