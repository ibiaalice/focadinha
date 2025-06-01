package com.bia.focadinha.android.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import androidx.compose.runtime.remember as remember

class HomeController {
    var timeLeft by mutableStateOf(25 * 60)
        private set

    var isRunning by mutableStateOf(false)
        private set

    fun start(){
        if(!isRunning){
            isRunning = true
        }
    }

    fun pause(){
        if(isRunning){
            isRunning = false
        }
    }

    fun reset(){
        isRunning = false
        timeLeft = 25 * 60
    }

    @Composable
    fun observeTimer(){
        LaunchedEffect(isRunning) {
            while (isRunning && timeLeft > 0 ){
                delay(1000L)
                timeLeft--
            }

            if (timeLeft == 0){
                isRunning = false
            }
        }
    }
}