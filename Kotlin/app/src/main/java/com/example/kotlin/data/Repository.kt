package com.example.kotlin.data


import com.example.kotlin.data.model.Note
import com.example.kotlin.data.provider.FireStoreProvider
import com.example.kotlin.data.provider.RemoteDataProvider



object Repository {

    private val remoteProvider: RemoteDataProvider = FireStoreProvider()

    fun getNotes() = remoteProvider.subscribeToAllNotes()
    fun saveNote(note: Note) = remoteProvider.saveNote(note)
    fun getNoteById(id: String) = remoteProvider.getNoteById(id)

}