package com.example.kotlin.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.example.kotlin.R
import com.example.kotlin.data.model.Note
import com.example.kotlin.ui.base.BaseActivity
import com.example.kotlin.ui.note.NoteActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity<List<Note>?, MainViewState>() {

    override val viewModel: MainViewModel by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }
    override val layoutRes: Int = R.layout.activity_main
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        adapter = MainAdapter( object : MainAdapter.OnItemClickListener {
            override fun onItemClick(note: Note) {
                openNoteScreen(note)
            }
        })
        mainRecycler.adapter = adapter

        fab.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                openNoteScreen(null)
            }
        })
    }

    override fun renderData(data: List<Note>?) {
        if (data == null) return
        adapter.notes = data
    }

    private fun openNoteScreen(note: Note?) {
        val intent = NoteActivity.getStartIntent(this, note?.id)
        startActivity(intent)
    }
}