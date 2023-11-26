package com.example.learn_kotlin_flows

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var event = findViewById<Button>(R.id.btn_event)
        var operator = findViewById<Button>(R.id.btn_operator)
        var non_terminal_operator = findViewById<Button>(R.id.btn_non_operator)

        event.setOnClickListener {
            activityEvent()
        }

        operator.setOnClickListener {
            activityOperator()
        }

        non_terminal_operator.setOnClickListener {
            activityNonOperator()
        }
    }

    private fun activityEvent() {
        startActivity(Intent(this, Events::class.java))
    }
    private fun activityOperator() {
        startActivity(Intent(this, Operators::class.java))
    }

    private fun activityNonOperator() {
        startActivity(Intent(this, NonTerminalOperators::class.java))
    }
}