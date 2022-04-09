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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                        modifier = Modifier.offset(y = 100.dp).shadow(10.dp)
                    ) {
                        val fahrenheitTextState = remember { mutableStateOf(TextFieldValue()) }
                        val celsiusTextState = remember { mutableStateOf(TextFieldValue()) }

                        val fahrenheitTextField = TextField(
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



                        val celsiusTextField = TextField(
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FirstAndroidAppTheme {
    }
}