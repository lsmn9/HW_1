package com.example.kotlin.ui.note

import androidx.lifecycle.Observer
import com.example.kotlin.data.Repository
import com.example.kotlin.data.model.Note
import com.example.kotlin.data.model.NoteResult
import com.example.kotlin.ui.base.BaseViewModel

class NoteViewModel(val repository: Repository) : BaseViewModel<NoteViewState.Data,
        NoteViewState>() {

    private val currentNote: Note?
        get() = viewStateLiveData.value?.data?.note
    private var pendingNote: Note? = null

    fun saveChanges(note: Note) {
        viewStateLiveData.value = NoteViewState(NoteViewState.Data(note = note))
    }

    override fun onCleared() {
        currentNote?.let { repository.saveNote(it) }
    }

    fun loadNote(noteId: String) {
        repository.getNoteById(noteId).observeForever { t ->
            t?.let {
                viewStateLiveData.value = when (t) {
                    is NoteResult.Success<*> -> NoteViewState(NoteViewState.Data(note = t.data as? Note))
                    is NoteResult.Error -> NoteViewState(error = t.error)
                }
            }
        }
    }

    fun deleteNote() {
        currentNote?.let {
            repository.deleteNote(it.id).observeForever { t ->
                t?.let {
                    viewStateLiveData.value = when (it) {
                        is NoteResult.Success<*> -> NoteViewState(NoteViewState.Data(isDeleted = true))
                        is NoteResult.Error -> NoteViewState(error = it.error)
                    }
                }
            }
        }
    }
}