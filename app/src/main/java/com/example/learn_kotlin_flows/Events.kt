package com.example.learn_kotlin_flows

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class Events : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sample)

        /**
         * Events Are Generally use for If we want to Perform Any Operation
         * Before Function Start, On Every Element Emit and On Completion
         * */
        GlobalScope.launch(Dispatchers.Main) {
            producer()
                .onStart {
                    /**
                     * Want to Perform Any Specific Emit()
                     * */
                    emit(0)
                    Log.d("FLOWS", "Starting Out")
                }
                .onCompletion {
                    /**
                     * Want to Perform Any Specific Emit()
                     * */
                    emit(6)
                    Log.d("FLOWS", "Completed")
                }
                .onEach {
                    Log.d("FLOWS", "About to Emit - $it")
                }
                .collect {
                    Log.d("FLOWS", it.toString())
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