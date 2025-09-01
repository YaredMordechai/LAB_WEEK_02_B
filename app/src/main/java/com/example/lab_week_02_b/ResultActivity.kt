package com.example.lab_week_02_b

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {
    companion object {
        const val COLOR_KEY = "COLOR_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)

        val colorCode = intent.getStringExtra(COLOR_KEY)

        val backgroundScreen = findViewById<ConstraintLayout>(R.id.background_screen)

        if (!colorCode.isNullOrEmpty()) {
            try {
                val cleanCode = colorCode.replace("#", "") // biar aman
                backgroundScreen.setBackgroundColor(Color.parseColor("#$cleanCode"))
            } catch (e: IllegalArgumentException) {
                Toast.makeText(this, "Kode warna tidak valid!", Toast.LENGTH_LONG).show()
            }
        }

        val resultMessage = findViewById<TextView>(R.id.color_code_result_message)
        resultMessage.text = getString(
            R.string.color_code_result_message,
            colorCode?.uppercase() ?: "UNKNOWN"
        )

        ViewCompat.setOnApplyWindowInsetsListener(backgroundScreen) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
