package com.example.kotlin.ui

import com.example.kotlin.data.model.Note
import com.example.kotlin.ui.base.BaseViewState


class MainViewState(notes: List<Note>? = null, error: Throwable? = null)
    : BaseViewState<List<Note>?>(notes, error)