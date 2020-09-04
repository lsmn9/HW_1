package com.example.kotlin

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.button)
        val nativeColor = button.currentTextColor
        button.setOnClickListener { if (button.currentTextColor != Color.YELLOW) button.setTextColor(Color.YELLOW) else button.setTextColor(nativeColor) }
    }
}