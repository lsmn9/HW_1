package com.example.kotlin.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.R
import com.example.kotlin.data.model.Note
import com.example.kotlin.extentions.getColorInt
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_note.*

class MainAdapter(private val onItemClickListener: ((Note) -> Unit)? = null) : RecyclerView.Adapter<MainAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(override val containerView: View) :
            RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(note: Note) {
            title.text = note.title
            body.text = note.body

            itemView.setBackgroundColor(note.color.getColorInt(itemView.context))
            itemView.setOnClickListener { onItemClickListener?.invoke(note) }

        }
    }
    var notes: List<Note> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int): Unit {
        holder.bind(notes[position])
    }
    interface OnItemClickListener {
        fun onItemClick(note: Note)
    }
}


