package com.meowmeowahr.formelements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormPage(textFieldValue: MutableState<String>,
             teamNumberValue: MutableState<String>,
             checkBoxValue: MutableState<Boolean>,
             switchValue: MutableState<Boolean>,
             colorValue: MutableState<Color>,
             ratingValue: MutableState<Int>,
) {
    val showDialog =  remember { mutableStateOf(false) }

    if (showDialog.value) {
        ColorDialog(showDialog = showDialog, colorValue = colorValue)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(PaddingValues(12.dp)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedTextField(
            value = textFieldValue.value,
            label = { Text("Text Field") },
            maxLines = 1,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                textFieldValue.value = it
            }
        )
        OutlinedTextField(
            value = teamNumberValue.value,
            label = { Text("Team Number") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            maxLines = 1,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                teamNumberValue.value = it
                    .replace("[^0-9]".toRegex(), "")
                    .take(4)
            }
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.padding(PaddingValues(16.dp)),
                text = "Checkbox"
            )
            Checkbox(
                checked = checkBoxValue.value,
                onCheckedChange = {
                    checkBoxValue.value = it
                }
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.padding(PaddingValues(16.dp)),
                text = "Switch"
            )
            Switch(
                checked = switchValue.value,
                onCheckedChange = {
                    switchValue.value = it
                }
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.padding(PaddingValues(16.dp)),
                text = "Color Select"
            )
            Box(modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .size(36.dp)
                .background(colorValue.value)
                .clickable { showDialog.value = true }
            ) {

            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.padding(PaddingValues(16.dp)),
                text = "Rating"
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Slider(
                    value = ratingValue.value.toFloat(),
                    onValueChange = { ratingValue.value = it.toInt() },
                    valueRange = 0f..5f,
                    steps = 4,
                    modifier = Modifier.width(256.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    ratingValue.value.toString(),
                    style = TextStyle(fontSize = 22.sp)
                )
            }
        }
    }
}