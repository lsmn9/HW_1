package com.example.kotlin.di

import com.example.kotlin.data.Repository
import com.example.kotlin.data.provider.FireStoreProvider
import com.example.kotlin.data.provider.RemoteDataProvider
import com.example.kotlin.ui.MainViewModel
import com.example.kotlin.ui.note.NoteViewModel
import com.example.kotlin.ui.splash.SplashViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.dsl.module.module

val appModule = module {
    single { FirebaseAuth.getInstance() }
    single { FirebaseFirestore.getInstance() }
    single { FireStoreProvider(get(), get()) } bind RemoteDataProvider::class
    single { Repository(get()) }
}

val splasModule = module {
    factory { SplashViewModel(get()) }
}

val mainModule = module {
    factory { MainViewModel(get()) }
}

val noteModule = module {
    factory { NoteViewModel(get()) }
}