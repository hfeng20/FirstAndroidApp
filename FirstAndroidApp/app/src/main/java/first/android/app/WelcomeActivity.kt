package first.android.app

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.ui.Modifier
import first.android.app.ui.theme.FirstAndroidAppTheme

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
                    TextButton (
                        onClick = {
                            //val intent = Intent(Intent.ACTION_VIEW)
                            val intent = Intent(this@WelcomeActivity, MainActivity::class.java);
                            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
                            overridePendingTransition(R.anim.slide_in_right, android.R.anim.fade_out)
                            this.finish()
                        },
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text("Welcome")
                    }
                }
            }
        }
    }
}
