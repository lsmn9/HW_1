package com.example.kotlin.ui.note

import androidx.lifecycle.ViewModel
import com.example.kotlin.data.Repository
import com.example.kotlin.data.model.Note

class NoteViewModel(private val repository: Repository = Repository) : ViewModel() {


    private var pendingNote: Note? = null


    fun saveChanges(note: Note) {
        pendingNote = note
    }

    override fun onCleared() {
        if (pendingNote != null) {
            repository.saveNote(pendingNote!!)
        }
    }


}