package com.example.learn_kotlin_flows

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class Operators : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sample)

        //firstOperator()
        toListOperator()
    }

    private fun firstOperator() {
        GlobalScope.launch(Dispatchers.Main) {
            /**
             * First Operator is Use For, Give the First Element
             * Operators are itself a Suspend Functions
             * */
            var result = producer().first()
            Log.d("FLOWS", result.toString())
        }
    }

    private fun toListOperator() {
        GlobalScope.launch(Dispatchers.Main) {
            /**
             * toList Operator is Use For, Contain All the Data in List and Emit it Once
             * Operators are itself a Suspend Functions
             * */
            var result = producer().toList()
            Log.d("FLOWS", result.toString())
        }
    }


    private fun producer() = flow<Int> {
        val list = listOf<Int>(1,2,3,4,5,6,7,8,9,10)
        list.forEach {
            delay(1000)
            emit(it)
        }
    }
}