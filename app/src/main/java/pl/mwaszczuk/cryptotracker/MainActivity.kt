package pl.mwaszczuk.cryptotracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import dagger.hilt.android.AndroidEntryPoint
import pl.mwaszczuk.design.theme.CryptoTrackerTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoTrackerTheme {
                Surface {
                    MainActivityComposable()
                }
            }
        }
    }
}
