package com.example.kotlin

import androidx.multidex.MultiDexApplication
import com.example.kotlin.di.appModule
import com.example.kotlin.di.mainModule
import com.example.kotlin.di.noteModule
import com.example.kotlin.di.splasModule
import org.koin.android.ext.android.startKoin

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(appModule, splasModule, mainModule, noteModule))
    }
}