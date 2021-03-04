/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TimerViewModel : ViewModel() {

    var started by mutableStateOf(false)
    var startedRoutine: Job = Job()

    var encoded by mutableStateOf(false)
    var showExplanation by mutableStateOf(true)

    var countdownTimer by mutableStateOf(0)

    val elementPairs = hashMapOf(
        Pair(1, 10),
        Pair(10, 60),
        Pair(60, 600),
        Pair(600, 3600),
        Pair(3600, 36000),
        Pair(36000, 60 * 60 * 24)
    )

    fun increaseTime(multiplier: Int) {
        countdownTimer += multiplier
        if (countdownTimer >= 60 * 60 * 24) countdownTimer = 60 * 60 * 24 - 1
    }

    fun decreaseTime(multiplier: Int) {
        countdownTimer -= multiplier
        if (countdownTimer < 0) countdownTimer = 0
    }

    fun statusChanged() {
        this.started = !started
        if (this.started) startTimer()
        else startedRoutine.cancel()
    }

    private fun startTimer() {
        startedRoutine.cancel()
        startedRoutine = viewModelScope.launch(Dispatchers.Default) {
            while (countdownTimer > 0) {
                delay(1000)
                countdownTimer--
            }
            started = false
        }
    }
}
