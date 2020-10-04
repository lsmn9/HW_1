package com.example.kotlin.ui


import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Observer
import com.example.kotlin.data.Repository
import com.example.kotlin.data.model.Note
import com.example.kotlin.data.model.NoteResult
import com.example.kotlin.data.model.NoteResult.Error
import com.example.kotlin.ui.base.BaseViewModel



class MainViewModel(val repository: Repository) : BaseViewModel<List<Note>?, MainViewState>() {

    private val notesObserver = object : Observer<NoteResult> {
    override fun onChanged(t: NoteResult?) {
        if (t == null) return

        when(t) {
            is NoteResult.Success<*> -> {
                viewStateLiveData.value = MainViewState(notes = t.data as? List<Note>)
            }
            is Error -> {
                viewStateLiveData.value = MainViewState(error = t.error)
            }
        }
    }
    }

    private val repositoryNotes = repository.getNotes()

    init {
        viewStateLiveData.value = MainViewState()
        repositoryNotes.observeForever(notesObserver)
    }
    @VisibleForTesting
    public override fun onCleared() {
        repositoryNotes.removeObserver(notesObserver)
    }
}

