package com.example.translator.application


import android.app.Application
import com.example.translator.di.AppComponent
import com.example.translator.di.DaggerAppComponent


class TranslatorApp : Application() {

    var appComp: AppComponent = DaggerAppComponent.builder()
        .application(this)
        .build()

}
