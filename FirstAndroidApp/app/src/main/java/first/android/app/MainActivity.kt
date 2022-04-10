package first.android.app

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import first.android.app.ui.theme.FirstAndroidAppTheme
import first.android.app.ui.theme.Purple200
import java.time.format.TextStyle

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstAndroidAppTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Row (
                        modifier = Modifier.offset(y = 300.dp)
                    ) {
                        val fahrenheitTextState = remember { mutableStateOf(TextFieldValue("F")) }
                        val celsiusTextState = remember { mutableStateOf(TextFieldValue("C")) }
                        val fahrenheitTextField = OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(0.5f),
                            value = fahrenheitTextState.value,
                            onValueChange = {
                                fahrenheitTextState.value = it
                                if(isNumeric(it.text)) {
                                    celsiusTextState.value = TextFieldValue(calculateConversion(fahrenheitTextState.value.text.toDouble(), false).toString())
                                }
                                else {
                                    celsiusTextState.value = TextFieldValue("Err")
                                }
                            }
                        )
                        val celsiusTextField = OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(1.0f),
                            value = celsiusTextState.value,
                            onValueChange = {
                                celsiusTextState.value = it
                                if(isNumeric(it.text)) {
                                    fahrenheitTextState.value = TextFieldValue(calculateConversion(celsiusTextState.value.text.toDouble(), true).toString())
                                }
                                else {
                                    fahrenheitTextState.value = TextFieldValue("Err")
                                }
                            }
                        )

                    }
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .offset(y = 400.dp)
                            .fillMaxSize()
                    ) {
                        Text("Convert between Fahrenheit and Celsius!", fontSize = 16.sp)
                    }
                }
            }
        }
    }
}
fun isNumeric(value: String): Boolean {
    if(value.isEmpty()) {
        return false
    }
    return value.toDoubleOrNull() != null
}

fun calculateConversion(value: Double, toCelsius: Boolean): Double {
    if(toCelsius) {
        return (9.0/5.0 * value) + 32.0
    }
    return (value - 32.0) * 5.0/9.0
}

fun test() {
    println("")
}

@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name), fontSize = 18.sp) },
        backgroundColor = Color(0xFFFFD700),
        contentColor = Color.White
    )
}

@Composable
fun BottomNavBar() {
    val items = listOf(
        NavigationItem.Conversion,
        NavigationItem.Users
    )
    BottomNavigation(
        backgroundColor = Color(0xFFFFD700),
        contentColor = Color.White
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(item.icon), contentDescription = item.title) },
                label = { Text(item.title) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = true,
                selected = false,
                onClick = {
                    /* Add code later */
                }
            )
        }
    }
}

@Composable
fun MainScreen() {
    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomNavBar() }
    ) {
        FirstAndroidAppTheme {

            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                Row(
                    modifier = Modifier.offset(y = 300.dp)
                ) {
                    val fahrenheitTextState = remember { mutableStateOf(TextFieldValue("F")) }
                    val celsiusTextState = remember { mutableStateOf(TextFieldValue("C")) }
                    val fahrenheitTextField = OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(0.5f),
                        value = fahrenheitTextState.value,
                        onValueChange = {
                            fahrenheitTextState.value = it
                            if (isNumeric(it.text)) {
                                celsiusTextState.value = TextFieldValue(
                                    calculateConversion(
                                        fahrenheitTextState.value.text.toDouble(),
                                        false
                                    ).toString()
                                )
                            } else {
                                celsiusTextState.value = TextFieldValue("Err")
                            }
                        }
                    )
                    val celsiusTextField = OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(1.0f),
                        value = celsiusTextState.value,
                        onValueChange = {
                            celsiusTextState.value = it
                            if (isNumeric(it.text)) {
                                fahrenheitTextState.value = TextFieldValue(
                                    calculateConversion(
                                        celsiusTextState.value.text.toDouble(),
                                        true
                                    ).toString()
                                )
                            } else {
                                fahrenheitTextState.value = TextFieldValue("Err")
                            }
                        }
                    )

                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .offset(y = 400.dp)
                        .fillMaxSize()
                ) {
                    Text("Convert between Fahrenheit and Celsius!", fontSize = 16.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FirstAndroidAppTheme {
    }
    MainScreen()
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar()
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    BottomNavBar()
}