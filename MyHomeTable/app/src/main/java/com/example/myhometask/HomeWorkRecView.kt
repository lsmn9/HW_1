package com.example.myhometask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HomeWorkRecView ( private var subjects: ArrayList<String>, private var work: ArrayList<String> ):
    RecyclerView.Adapter<HomeWorkRecView.HomeTaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, vt: Int): HomeTaskViewHolder {

        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_homework, parent, false)
        return HomeTaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeTaskViewHolder, position: Int) {
        holder.subject.text = subjects.get(position)
        holder.work.text = work.get(position)
    }

    override fun getItemCount(): Int {
        return subjects.size

    }

    class HomeTaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var subject: TextView = itemView.findViewById(R.id.subject_home)
        var work: TextView = itemView.findViewById(R.id.work_home)
    }
}