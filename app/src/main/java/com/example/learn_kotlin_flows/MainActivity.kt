package com.example.learn_kotlin_flows

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //launchFlow()

        //cancelFlow()

        multipleConsumer()
    }

    private fun launchFlow() {
        GlobalScope.launch {
            val data: Flow<Int> = producer()
            data.collect {
                Log.d("FLOWS", it.toString())
            }
        }
    }

    private fun cancelFlow() {
        val job = GlobalScope.launch {
            val data: Flow<Int> = producer()
            data.collect {
                Log.d("FLOWS", it.toString())
            }
        }

        GlobalScope.launch {
            delay(3500)
            job.cancel()
        }
    }

    private fun multipleConsumer() {
        GlobalScope.launch {
            val data: Flow<Int> = producer()
            data.collect {
                Log.d("FLOWS 1 - ", it.toString())
            }
        }

        /**
         * After Adding Delay Of 2.5 Sec
         * Still Collect function Fetch the data from Beginning
         * */
        GlobalScope.launch {
            val data: Flow<Int> = producer()
            delay(2500)
            data.collect {
                Log.d("FLOWS 2 - ", it.toString())
            }
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