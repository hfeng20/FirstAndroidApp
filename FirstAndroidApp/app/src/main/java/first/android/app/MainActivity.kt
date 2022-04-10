package first.android.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import first.android.app.ui.theme.FirstAndroidAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
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
        backgroundColor = Color(0xFFAA6C39),
        contentColor = Color.White
    )
}
@Composable
fun BottomNavBar(navController: NavController) {
    val items = listOf(
        NavigationItem.Conversion,
        NavigationItem.Users
    )
    BottomNavigation(
        backgroundColor = Color(0xFFAA6C39),
        contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(item.icon), contentDescription = item.title) },
                label = { Text(item.title) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun MainScreen() {
    var navController = rememberNavController()
    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomNavBar(navController) }
    ) {
        FirstAndroidAppTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                Navigation(navController)
            }
        }
    }
}

@Composable
fun ConversionsScreen() {
    Row(
        modifier = Modifier.offset(y = 200.dp)
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
            .offset(y = 300.dp)
            .fillMaxSize()
    ) {
        Text("Convert between Fahrenheit and Celsius!", fontSize = 16.sp)
    }
}

@Composable
fun UsersScreen() {

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

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.Conversion.route) {
        composable(NavigationItem.Conversion.route) {
            ConversionsScreen()
        }
        composable(NavigationItem.Users.route) {
            UsersScreen()
        }
    }
}