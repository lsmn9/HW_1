package com.example.matdis.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.matdis.R
import kotlinx.android.synthetic.main.recyclerview_note.view.*


class NotesAdapter( ) : RecyclerView.Adapter<NotesAdapter.MyViewHolder>() {


    var data = arrayListOf(
        "заметка1","заметка2","заметка3", "заметка4"
    )

   inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

var noteHeader : TextView? = null
var noteBody: TextView? = null

        init {
            noteHeader = itemView.note_header
            noteBody = itemView.note_body

            itemView.moveItemUp2?.setOnClickListener { moveUp() }
            itemView.moveItemDown2?.setOnClickListener { moveDown() }
        }

        private fun moveDown() {
            layoutPosition.takeIf { it < data.size - 1 }?.also { currentPosition ->
                data.removeAt(currentPosition).apply {
                    data.add(currentPosition + 1, this)
                }
                notifyItemMoved(currentPosition, currentPosition + 1)
            }
        }

        private fun moveUp() {
            layoutPosition.takeIf { it>0 }?.also { currentPosition ->
                data.removeAt(currentPosition).apply {
                    data.add(currentPosition - 1, this)
                }
                notifyItemMoved(currentPosition, currentPosition - 1)
            }
        }


   }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent?.context).
        inflate(R.layout.recyclerview_note, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.noteHeader?.text = data[position]
        holder.noteBody?.text =  "${holder.noteBody?.text}  $position"

    }
}