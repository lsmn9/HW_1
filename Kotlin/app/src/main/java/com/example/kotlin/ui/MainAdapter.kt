package com.example.kotlin.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.R
<<<<<<< HEAD
import com.example.kotlin.data.model.Color
import com.example.kotlin.data.model.Note

class MainAdapter(private val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<MainAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title = itemView.findViewById<TextView>(R.id.title)
        private val body = itemView.findViewById<TextView>(R.id.body)

        fun bind(note: Note) {
            val color = when (note.color) {
                Color.WHITE -> R.color.color_white
                Color.VIOLET -> R.color.color_violet
                Color.YELLOW -> R.color.color_yello
                Color.RED -> R.color.color_red
                Color.PINK -> R.color.color_pink
                Color.GREEN -> R.color.color_green
                Color.BLUE -> R.color.color_blue
            }

            itemView.setBackgroundColor(itemView.context.resources.getColor(color))
            title.text = note.title
            body.text = note.note
            itemView.setOnClickListener { onItemClickListener.onItemClick(note)}
        }
    }
=======
import com.example.kotlin.data.model.Note

class MainAdapter : RecyclerView.Adapter<NoteViewHolder>() {

>>>>>>> 5a17e6d087ceb108bb43ae6073369653655144e9
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
<<<<<<< HEAD
    interface OnItemClickListener {
        fun onItemClick(note: Note)
    }
}


=======
}

class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val title = itemView.findViewById<TextView>(R.id.title)
    private val body = itemView.findViewById<TextView>(R.id.body)

    fun bind(note: Note) {
        title.text = note.title
        body.text = note.note
        itemView.setBackgroundColor(note.color)
    }
}
>>>>>>> 5a17e6d087ceb108bb43ae6073369653655144e9
