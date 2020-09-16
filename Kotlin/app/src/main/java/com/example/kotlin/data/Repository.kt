package com.example.kotlin.data

<<<<<<< HEAD
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlin.data.model.Color
import com.example.kotlin.data.model.Note
import java.util.*
=======
import com.example.kotlin.data.model.Note
>>>>>>> 5a17e6d087ceb108bb43ae6073369653655144e9


object Repository {

<<<<<<< HEAD
    private val notesLiveData = MutableLiveData<List<Note>>()

    private val notes: MutableList<Note> = mutableListOf(
            Note(id = UUID.randomUUID().toString(),
                    title = "Моя первая заметка",
                    note = "Kotlin очень краткий, но при этом выразительный язык",
                    color = Color.WHITE),
            Note(id = UUID.randomUUID().toString(),
                    title = "Моя первая заметка",
                    note = "Kotlin очень краткий, но при этом выразительный язык",
                    color = Color.BLUE),
            Note(id = UUID.randomUUID().toString(),
                    title = "Моя первая заметка",
                    note = "Kotlin очень краткий, но при этом выразительный язык",
                    color = Color.GREEN),
            Note(id = UUID.randomUUID().toString(),
                    title = "Моя первая заметка",
                    note = "Kotlin очень краткий, но при этом выразительный язык",
                    color = Color.PINK),
            Note(id = UUID.randomUUID().toString(),
                    title = "Моя первая заметка",
                    note = "Kotlin очень краткий, но при этом выразительный язык",
                    color = Color.RED),
            Note(id = UUID.randomUUID().toString(),
                    title = "Моя первая заметка",
                    note = "Kotlin очень краткий, но при этом выразительный язык",
                    color = Color.YELLOW),
            Note(id = UUID.randomUUID().toString(),
                    title = "Моя первая заметка",
                    note = "Kotlin очень краткий, но при этом выразительный язык",
                    color = Color.VIOLET)
    )


    init {
        notesLiveData.value = notes
    }

    fun getNotes(): LiveData<List<Note>> {
        return notesLiveData
    }

    fun saveNote(note: Note) {
        addOrReplace(note)
        notesLiveData.value = notes
    }

    private fun addOrReplace(note: Note) {

        for (i in 0 until notes.size) {
            if (notes[i] == note) {
                notes.set(i, note)
                return
            }
        }

        notes.add(note)
    }

=======
    private val notes: List<Note>

    init {
        notes = listOf(
                Note("Моя первая заметка",
                        "Kotlin очень краткий, но при этом выразительный язык",
                        0xfff06292.toInt()),
                Note("Моя первая заметка",
                        "Kotlin очень краткий, но при этом выразительный язык",
                        0xff9575cd.toInt()),
                Note("Моя первая заметка",
                        "Kotlin очень краткий, но при этом выразительный язык",
                        0xff64b5f6.toInt()),
                Note("Моя первая заметка",
                        "Kotlin очень краткий, но при этом выразительный язык",
                        0xff4db6ac.toInt()),
                Note("Моя первая заметка",
                        "Kotlin очень краткий, но при этом выразительный язык",
                        0xffb2ff59.toInt()),
                Note("Моя первая заметка",
                        "Kotlin очень краткий, но при этом выразительный язык",
                        0xffffeb3b.toInt()),
                Note("Моя первая заметка",
                        "Kotlin очень краткий, но при этом выразительный язык",
                        0xffff6e40.toInt())
        )
    }

    fun getNotes(): List<Note> {
        return notes
    }
>>>>>>> 5a17e6d087ceb108bb43ae6073369653655144e9
}