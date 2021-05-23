package com.example.matdis.notes

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.matdis.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_all_notes.view.*


class NotesAdapter() : RecyclerView.Adapter<NotesAdapter.MyViewHolder>() {

    var data = arrayListOf(
        "заметка1", "заметка2", "заметка3", "заметка4"
    )

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var noteHeader: TextView? = null
        var noteBody: TextView? = null
        var fab: FloatingActionButton

        init {
            fab = itemView.fab_to_add
            noteHeader = itemView.note_header
            noteBody = itemView.note_body

            itemView.fab_to_add?.setOnClickListener { addItem() }
            itemView.moveItemUp2?.setOnClickListener { moveUp() }
            itemView.moveItemDown2?.setOnClickListener { moveDown() }
        }


        private fun addItem() {
            data.add(layoutPosition, "новая заметка" + "$position")
            notifyItemInserted(layoutPosition)
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
            layoutPosition.takeIf { it > 0 }?.also { currentPosition ->
                data.removeAt(currentPosition).apply {
                    data.add(currentPosition - 1, this)
                }
                notifyItemMoved(currentPosition, currentPosition - 1)
            }
        }

    }

    fun onItemMove(fromPosition: Int, toPosition: Int) {
        data.removeAt(fromPosition).apply {
            data.add(if (toPosition > fromPosition) toPosition - 1 else toPosition, this)
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    fun onItemDismiss(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent?.context).inflate(R.layout.fragment_all_notes, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var description = SpannableString("${holder.noteBody?.text}  $position")
        if (position % 2 == 0) {
            description.setSpan(
                ForegroundColorSpan(Color.RED), 0, 10, Spannable.SPAN_EXCLUSIVE_INCLUSIVE
            )
        } else description.setSpan(
            ForegroundColorSpan(Color.BLUE), 0, 10, Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        holder.noteHeader?.text = data[position]
        holder.noteBody?.text = description


    }


}