package com.example.matdis.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.matdis.R
import com.example.matdis.chips.ChipsFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_all_notes.*
import kotlinx.android.synthetic.main.fragment_all_notes.view.*
import kotlinx.android.synthetic.main.recycleview_all_notes.*


class AllNotesFragment:Fragment() {

    val adapter = NotesAdapter()
    private lateinit var itemTouchHelper: ItemTouchHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.recycleview_all_notes, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notes_RV.layoutManager = LinearLayoutManager(context)
        notes_RV.adapter = adapter
        itemTouchHelper = ItemTouchHelper(ITHelper(adapter))
        itemTouchHelper.attachToRecyclerView(notes_RV)

    }


}