package com.example.learn_kotlin_flows

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

class NonTerminalOperators : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sample)

        //nonTerminalOperator()
        //getNotesWithNonTerminalOperator()
        workingOfBufferOperator()
    }

    private fun nonTerminalOperator() {
        /**
         * map() anf filter() are Non Terminal Operators
         * They itself provide a Flow, it generally used for
         * data modification
         * */
        GlobalScope.launch(Dispatchers.Main) {
            producer()
                .map {
                    it * 2
                }
                .filter {
                    it < 8
                }
                .collect {
                    Log.d("FLOWS", it.toString())
                }

        }
    }

    private fun producer() = flow<Int> {
        val list = listOf<Int>(1,2,3,4,5)
        list.forEach {
            delay(1000)
            emit(it)
        }
    }

    private fun getNotesWithNonTerminalOperator() {
        GlobalScope.launch(Dispatchers.Main) {
            getNotes()
                .map {
                    FormattedNotes(it.isActive, it.title.uppercase(), it.description)
                }
                .filter {
                    it.isActive
                }
                .collect {
                    Log.d("FLOWS", it.toString())
                }
        }
    }

    private fun getNotes(): Flow<Notes> {
        val list = listOf(
            Notes(1, true, "First", "First Description") ,
            Notes(2, true, "Second", "Second Description") ,
            Notes(3, false, "Third", "Third Description")
        )
        return list.asFlow()
    }

    private fun workingOfBufferOperator() {
        /**
         * buffer() Operator is use for, Store the Data and Provide to Consumer
         * if Consumer are Slow to consume the data
         * */
        GlobalScope.launch(Dispatchers.Main) {
            val time = measureTimeMillis {
                producer()
                    .buffer(3)
                    .collect {
                        delay(1500)
                        Log.d("FLOWS", it.toString())
                    }
            }
            Log.d("FLOWS", time.toString())
        }
    }
}

data class Notes(val id: Int, val isActive: Boolean, val title: String, val description: String)
data class FormattedNotes(val isActive: Boolean, val title: String, val description: String)