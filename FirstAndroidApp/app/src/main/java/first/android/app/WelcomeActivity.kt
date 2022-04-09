package first.android.app

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import first.android.app.ui.theme.FirstAndroidAppTheme
import first.android.app.ui.theme.White

class WelcomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstAndroidAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        TextButton (
                            onClick = {
                                //val intent = Intent(Intent.ACTION_VIEW)
                                val intent = Intent(this@WelcomeActivity, MainActivity::class.java);
                                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this@WelcomeActivity).toBundle())
                                overridePendingTransition(R.anim.slide_in_right, android.R.anim.fade_out)
                                this@WelcomeActivity.finish()
                            },
                            modifier = Modifier.fillMaxWidth().fillMaxHeight(.2f)
                        ) {
                            Text("Harry Feng's First Android App", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = White)
                        }
                        Text("Tap anywhere to proceed...", fontSize = 16.sp, color = White)
                    }
                }
            }
        }
    }
}
