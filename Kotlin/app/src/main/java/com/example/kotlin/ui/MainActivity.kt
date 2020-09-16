package com.example.kotlin.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.R
import com.example.kotlin.data.model.Note
import com.example.kotlin.ui.note.NoteActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var adapter: MainAdapter

    private fun openNoteScreen(note: Note?) {
        val intent = NoteActivity.getStartIntent(this, note)
        Log.d("!!!!!!!Main!!!!!!!!!!", note.toString())
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        adapter = MainAdapter(object : MainAdapter.OnItemClickListener {
            override fun onItemClick(note: Note) {
                openNoteScreen(note)
            }
        })
        fab.setOnClickListener { openNoteScreen(null) }
        val mainRecycler = findViewById<RecyclerView>(R.id.mainRecycler)
        mainRecycler.adapter = adapter

        viewModel.viewState().observe(this, Observer<MainViewState> { t ->
            t?.let { adapter.notes = it.notes }
        })
    }
}