package com.example.kotlin.ui.note

import com.example.kotlin.data.model.Note
import com.example.kotlin.ui.base.BaseViewState

class NoteViewState(note: Note? = null, error: Throwable? = null) : BaseViewState<Note?>(note, error)