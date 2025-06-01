@file:OptIn(ExperimentalMaterial3Api::class)

package com.bia.focadinha.android.home

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlin.text.*
import com.bia.focadinha.android.R


@SuppressLint("DefaultLocale")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val controller = remember { HomeController() }
    controller.observeTimer()

    val minutes = controller.timeLeft / 60
    val seconds = controller.timeLeft % 60
    val formattedTime = String.format("%02d:%02d", minutes, seconds)

    var showStopDialog: Boolean by remember { mutableStateOf(false) }


    Scaffold(
        topBar = {  TopAppBar(
            title = {
                Text(text = "Focadinha")
            }
        ) },

        floatingActionButton = {
            var text = "Focar"
            if (controller.isRunning) text = "Parar"

            var icon = Icons.Default.PlayArrow
            if (controller.isRunning) icon = Icons.Sharp.Close

            ExtendedFloatingActionButton(
                text = {
                    Text(text)
                       },
                icon = {
                    Icon(icon, contentDescription = "Focar") },
                onClick = {
                    if(controller.isRunning){
                        showStopDialog = true
                    } else{
                        controller.start()
                    }

                }
            )
        },

        content = { paddingValues -> Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues).padding(
                    horizontal = 24.dp,
                    vertical = 50.dp
                )
        ){
           TomatoImage(isRunning = controller.isRunning)
            Spacer(modifier = Modifier.height(60.dp))
            AnimatedVisibility(visible = !controller.isRunning) {
               PomodoroIntro()
           }
            Spacer(modifier = Modifier.height(32.dp))
            PomodoroTimerDisplay(timerText = formattedTime,
                modifier = Modifier.align(Alignment.CenterHorizontally)
                )
        }
        }

    )

    if(showStopDialog){
        ConfirmStopDialog(
            onConfirm = {
                controller.reset()
                showStopDialog = false
            },
            onDismiss = {
                showStopDialog = false
            }
        )
    }
}

@Composable
fun PomodoroIntro(){
    Column {
        Text( text = "O que é Pomodoro?",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text( text = "A técnica Pomodoro consiste em focar por 25 minutos e descansar por 5. Após quatro ciclos, faça uma pausa maior.",
            style = MaterialTheme.typography.bodySmall
        )

    }
}

@Composable
fun PomodoroTimerDisplay(timerText: String,  modifier: Modifier){
    Text(
        text = timerText,
        style = MaterialTheme.typography.displayLarge,
        modifier = modifier
    )
}

@Composable
fun ConfirmStopDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
){
  AlertDialog(
      onDismissRequest = onDismiss,
      text = {
          Text("Tem certeza que você deseja interromper o foco atual?")
             },
      confirmButton = {
          TextButton(onClick = onConfirm,
              colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.onSurface)
          ) { Text("Sim") }
                      },
      dismissButton = {
          TextButton(onClick = onDismiss,
              colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.onSurface)
          ) {
              Text("Cancelar")
          }
      }

  )
}

@Composable
fun TomatoImage(isRunning: Boolean){
    if(isRunning){
        Image(
            painter = painterResource(id = R.drawable.tomato_reading),
            contentDescription = "Tomate lendo",
            modifier = Modifier.fillMaxWidth().height(200.dp),
            contentScale = ContentScale.Fit
        )
    }else{
        Image(
            painter = painterResource(id = R.drawable.tomato),
            contentDescription = "Tomate lendo",
            modifier = Modifier.fillMaxWidth().height(200.dp),
            contentScale = ContentScale.Fit
        )
    }
}