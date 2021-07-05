package com.example.myhometask

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.ChronoUnit

@RequiresApi(Build.VERSION_CODES.O)
class HomeFragment : Fragment() {

    private lateinit var adapter: ClassesRecView
    private lateinit var recyclerViewSub: RecyclerView
    private lateinit var recyclerViewHW: RecyclerView
    private val EXAM_DAY =  LocalDate.parse("2022-01-10")
    private val EXAM_TIME = LocalTime.of(9, 30)
    private val EXAM = LocalDateTime.of(EXAM_DAY, EXAM_TIME)
    private lateinit var timeToExamDay: TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ClassesRecView((activity as MainActivity).getSubjects())
        recyclerViewSub = view.findViewById(R.id.rv_home_lessons)
        recyclerViewSub.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewSub.adapter = adapter

        recyclerViewHW = view.findViewById(R.id.rv_home_work)
        recyclerViewHW.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewHW.adapter =
            HomeWorkRecView((activity as MainActivity).getSubjects(),
                (activity as MainActivity).getHomework())

        timeToExamDay = view.findViewById(R.id.time_to_exam)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val days = LocalDateTime.now().until(EXAM, ChronoUnit.DAYS)
        val hours = LocalDateTime.now().until(EXAM, ChronoUnit.HOURS) - (days * 24)
        val minutes =LocalDateTime.now().until(EXAM, ChronoUnit.MINUTES) -
                (LocalDateTime.now().until(EXAM, ChronoUnit.HOURS)*60)
        timeToExamDay.text ="$days дней "  + "$hours часов " + "$minutes минут"


    }
}