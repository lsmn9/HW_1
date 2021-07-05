package com.example.myhometask

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var search: ImageView
    private lateinit var btmnav: BottomNavigationView
    private lateinit var head: TextView
    private lateinit var skyper: TextView
    private var subjects = arrayListOf<String>()
    private var work = arrayListOf<String>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        search = findViewById(R.id.search)
        btmnav = findViewById(R.id.bottom_navigation_view)
        skyper = findViewById(R.id.skyper_main)
        head = findViewById(R.id.head)
        subjects.add("history")
        work.add("toDoHistory")
        subjects.add( "literature")
        work.add("toDoLiterature")
        subjects.add( "math")
        work.add("toDoMath")

        skyper.setOnClickListener {
            var browser = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.skype.com/ru/"))
            startActivity(browser) }

        search.setOnClickListener{ Toast.makeText(this, "Поиск пока не работает", Toast.LENGTH_SHORT).show()}
        btmnav.setOnNavigationItemSelectedListener {
            onNavItemSelected(
                it
            )
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun onNavItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.navigation_home -> {
                head.text = "Hi, Mike"
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, HomeFragment())
                    .commitNow()
            }
            R.id.navigation_classes -> {
                head.text = "Classes"
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, ClassesFragment())
                    .commitNow()
            }
            R.id.navigation_favour -> {
                head.text = ""

                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, MockFragment())
                    .commitNow()

                supportFragmentManager.findFragmentById(R.id.fragment_container)
                    ?.view?.findViewById<TextView>(R.id.mock_info)?.text =  "Здесь будут избранные"
            }
            R.id.navigation_list -> {
                head.text = ""
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, MockFragment())
                    .commitNow()

                supportFragmentManager.findFragmentById(R.id.fragment_container)
                    ?.view?.findViewById<TextView>(R.id.mock_info)?.text =  "Здесь будут заметки"
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun getSubjects(): ArrayList<String> {
        return subjects
    }
    fun getHomework(): ArrayList<String>{
        return work
    }
}