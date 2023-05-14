package com.meowmeowahr.formelements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


val PickerColors: List<Color> = listOf(
    Color(0xffF44336),
    Color(0xffE91E63),
    Color(0xff9C27B0),
    Color(0xff673AB7),
    Color(0xff3F51B5),
    Color(0xff2196F3),
    Color(0xff03A9F4),
    Color(0xff00BCD4),
    Color(0xff00ACC1),
    Color(0xff4CAF50),
    Color(0xff8BC34A),
    Color(0xffCDDC39),
    Color(0xffFFEB3B),
    Color(0xffFFC107),
    Color(0xffFF9800),
    Color(0xffFF5722),
    Color(0xff795548),
    Color(0xff9E9E9E),
    Color(0xff607D8B)
)

@Composable
fun ColorDialog(showDialog: MutableState<Boolean>, colorValue: MutableState<Color>) {
    AlertDialog(
        onDismissRequest = {
            showDialog.value = false
        },
        confirmButton = {
            TextButton(
                onClick = {
                    showDialog.value = false
                }
            ) {
                Text(text = "OK")
            }
        },
        text = {
            Column {
                Text(
                    text = "Select color",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 36.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(PickerColors.size) {
                        Box (
                            modifier = Modifier
                                .size(36.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(PickerColors[it])
                                .clickable { colorValue.value = PickerColors[it] },
                            contentAlignment = Alignment.Center
                        ) {
                            if (colorValue.value == PickerColors[it]) {
                                Icon(Icons.Default.Check, "")
                            }
                        }
                    }
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
    )
}