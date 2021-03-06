package com.example.kotlin.data.provider

import androidx.lifecycle.LiveData
import com.example.kotlin.data.model.Note
import com.example.kotlin.data.model.NoteResult
import com.example.kotlin.data.model.User

interface RemoteDataProvider {

    fun subscribeToAllNotes(): LiveData<NoteResult>
    fun getNoteById(id: String): LiveData<NoteResult>
    fun saveNote(note: Note) : LiveData<NoteResult>
    fun getCurrentUser(): LiveData<User?>
    fun deleteNote(noteId: String): LiveData<NoteResult>
}