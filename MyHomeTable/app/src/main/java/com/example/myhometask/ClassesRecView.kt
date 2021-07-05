package com.example.myhometask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ClassesRecView ( private var subjects: ArrayList<String> ):
    RecyclerView.Adapter<ClassesRecView.ClassViewHolder>() {

    var tryToSkype = false

    fun clicked():Boolean{
        println("kdjdskj" + tryToSkype)
       return tryToSkype
    }

    override fun onCreateViewHolder(parent: ViewGroup, vt: Int): ClassViewHolder {

        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_classes, parent, false)
        return ClassViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClassViewHolder, position: Int) {
        holder.subject.text = subjects.get(position)
    }

    override fun getItemCount(): Int {
        return subjects.size

    }

    class ClassViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var subject: TextView = itemView.findViewById(R.id.subject_classes)
        var skyper: TextView = itemView.findViewById(R.id.skyper)
    }
}