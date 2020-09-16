package com.example.kotlin.ui

import android.os.Bundle
<<<<<<< HEAD
import android.util.Log
=======
>>>>>>> 5a17e6d087ceb108bb43ae6073369653655144e9
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.R
<<<<<<< HEAD
import com.example.kotlin.data.model.Note
import com.example.kotlin.ui.note.NoteActivity
import kotlinx.android.synthetic.main.activity_main.*
=======
>>>>>>> 5a17e6d087ceb108bb43ae6073369653655144e9


class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var adapter: MainAdapter

<<<<<<< HEAD
    private fun openNoteScreen(note: Note?) {
        val intent = NoteActivity.getStartIntent(this, note)
        Log.d("!!!!!!!Main!!!!!!!!!!", note.toString())
        startActivity(intent)
    }

=======
>>>>>>> 5a17e6d087ceb108bb43ae6073369653655144e9
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
<<<<<<< HEAD
        adapter = MainAdapter(object : MainAdapter.OnItemClickListener {
            override fun onItemClick(note: Note) {
                openNoteScreen(note)
            }
        })
        fab.setOnClickListener { openNoteScreen(null) }
=======
        adapter = MainAdapter()
>>>>>>> 5a17e6d087ceb108bb43ae6073369653655144e9
        val mainRecycler = findViewById<RecyclerView>(R.id.mainRecycler)
        mainRecycler.adapter = adapter

        viewModel.viewState().observe(this, Observer<MainViewState> { t ->
            t?.let { adapter.notes = it.notes }
        })
    }
}