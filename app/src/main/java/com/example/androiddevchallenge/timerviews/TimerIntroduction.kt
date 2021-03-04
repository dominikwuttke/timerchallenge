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
package com.example.androiddevchallenge.timerviews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.TimerViewModel
import com.example.androiddevchallenge.ui.theme.typography

@Composable
fun TimerExplanation() {
    val viewModel = viewModel<TimerViewModel>()
    if (viewModel.showExplanation) {
        TimerExplanationText()
        TimerEncodingCheckbox(checkValue = viewModel.encoded, onValueChanged = { viewModel.encoded = it })
        HideTimerExplanation(hideExplanation = { viewModel.showExplanation = false })
    } else {
        ShowTimerExplanation(showExplanation = { viewModel.showExplanation = true })
    }
}

@Composable
private fun TimerExplanationText() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = "This is a special Countdown timer with encoded numbers. You can disable the encoding by checking the Checkbox.", style = typography.body1)
    }
}

@Composable
private fun TimerEncodingCheckbox(checkValue: Boolean, onValueChanged: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "Encode the timer?", style = typography.body1)
        Checkbox(checked = checkValue, onCheckedChange = { onValueChanged(it) })
    }
}

@Composable
private fun HideTimerExplanation(hideExplanation: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = { hideExplanation() }) {
            Text(text = "Hide explanation", style = typography.body1)
        }
    }
}

@Composable
private fun ShowTimerExplanation(showExplanation: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = { showExplanation() }) {
            Text(text = "Show explanation", style = typography.body1)
        }
    }
}
