package com.example.lab_week_02_b

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class ResultActivity : AppCompatActivity() {
    companion object {
        const val COLOR_KEY = "COLOR_KEY"
        const val ERROR_KEY = "ERROR_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val colorCode = intent.getStringExtra(COLOR_KEY)
        val backgroundScreen = findViewById<ConstraintLayout>(R.id.background_screen)

        if (!colorCode.isNullOrEmpty()) {
            try {
                // coba apply warna
                backgroundScreen.setBackgroundColor(Color.parseColor("#$colorCode"))

                val resultMessage = findViewById<TextView>(R.id.color_code_result_message)
                resultMessage.text = getString(
                    R.string.color_code_result_message,
                    colorCode.uppercase()
                )

            } catch (ex: IllegalArgumentException) {
                // kalau invalid, kirim error ke MainActivity
                Intent().also { errorIntent ->
                    errorIntent.putExtra(ERROR_KEY, true)
                    setResult(Activity.RESULT_OK, errorIntent)
                    finish()
                }
            }
            val resultMessage =
                findViewById<TextView>(R.id.color_code_result_message)
            resultMessage.text = getString(R.string.color_code_result_message,
                colorCode?.uppercase())
        }
        }
    }
