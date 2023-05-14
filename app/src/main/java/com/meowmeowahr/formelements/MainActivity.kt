package com.meowmeowahr.formelements

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.meowmeowahr.formelements.ui.theme.FormElementsTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FormElementsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RootPage()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootPage() {
    val navController = rememberNavController()
    val selectedIndex = remember { mutableStateOf(0) }

    val textFieldValue = remember {
        mutableStateOf("")
    }

    val teamNumberValue = remember {
        mutableStateOf("")
    }

    val checkBoxValue = remember {
        mutableStateOf(false)
    }

    val switchValue = remember {
        mutableStateOf(false)
    }

    val colorValue = remember {
        mutableStateOf(PickerColors[0])
    }

    val ratingValue = remember {
        mutableStateOf(5)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text("My App")
            })
        },
        bottomBar = { NavigationBar {
            NavigationBarItem(icon = {
                Icon(painterResource(R.drawable.baseline_list_alt_24),"")
            },
                label = { Text(text = "Form") },
                selected = (selectedIndex.value == 0),
                onClick = {
                    selectedIndex.value = 0
                    navController.navigate("form")
                })

            NavigationBarItem(icon = {
                Icon(painterResource(R.drawable.baseline_outbox_24), "")
            },
                label = { Text(text = "Output") },
                selected = (selectedIndex.value == 1),
                onClick = {
                    selectedIndex.value = 1
                    navController.navigate("output")
                })
        } }
    ) { contentPadding ->

        NavHost(navController = navController, startDestination = "form", modifier = Modifier.padding(contentPadding)) {
            composable("form") {
                FormPage(
                    textFieldValue,
                    teamNumberValue,
                    checkBoxValue,
                    switchValue,
                    colorValue,
                    ratingValue
                )
            }
            composable("output") {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(PaddingValues(48.dp))
                        .fillMaxWidth()) {
                    Icon(painterResource(R.drawable.outline_poll_192), "", modifier = Modifier.size(192.dp))
                    Column {
                        TableItem(name = "Text Field", data = textFieldValue.value)
                        TableItem(name = "Team Number", data = teamNumberValue.value)
                        TableItem(name = "Checkbox", data = checkBoxValue.value.toString())
                        TableItem(name = "Switch", data = switchValue.value.toString())
                        TableItem(name = "Color Select", data = Integer.toHexString(colorValue.value.toArgb()))
                        TableItem(name = "Rating", data = ratingValue.value.toString(), suffixIcon = Icons.Rounded.Star)
                    }
                }
            }
        }

    }
}


@Composable
fun TableItem(name: String, data: String, suffixIcon: ImageVector? = null) {
    Row(
        modifier = Modifier
            .padding(PaddingValues(12.dp))
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Text(name)
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (data == "") {
                Text(text = "Empty")
            } else {
                Text(text = data)
            }
            if (suffixIcon != null) {
                Icon(
                    suffixIcon, ""
                )
            }
        }
    }
    Divider()
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppPreview() {
    FormElementsTheme {
        RootPage()
    }
}

@Preview(showBackground = true)
@Composable
fun FormPreview() {
    val textFieldValue = remember {
        mutableStateOf("")
    }

    val teamNumberValue = remember {
        mutableStateOf("")
    }

    val checkBoxValue = remember {
        mutableStateOf(false)
    }

    val switchValue = remember {
        mutableStateOf(false)
    }

    val colorValue = remember {
        mutableStateOf(Color(0xFFFF00FF))
    }

    val ratingValue = remember {
        mutableStateOf(5)
    }

    FormElementsTheme {
        FormPage(
            textFieldValue = textFieldValue,
            teamNumberValue = teamNumberValue,
            checkBoxValue = checkBoxValue,
            switchValue = switchValue,
            colorValue = colorValue,
            ratingValue = ratingValue
        )
    }
}