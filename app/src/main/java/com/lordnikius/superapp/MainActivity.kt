package com.lordnikius.superapp

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.LocaleManagerCompat
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.lordnikius.superapp.stretching.StretchingRoutineScreen
import com.lordnikius.superapp.stretching.StretchingRoutineViewModel
import com.lordnikius.superapp.theme.AppTheme
import com.lordnikius.superapp.util.StringUiData
import com.lordnikius.superapp.util.locale.LocaleManager
import com.lordnikius.superapp.util.textToSpeech.TextToSpeechManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    internal lateinit var localeManager: LocaleManager

    private val viewModel by viewModels<StretchingRoutineViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                StretchingRoutineScreen(
                    viewModel
                )
            }
        }
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        viewModel.uiState
            .flowWithLifecycle(lifecycle)
            .onEach { uiState ->
                if (uiState.messageToSay != StringUiData.Empty) {
                    viewModel.onMessageSaid()
                }
            }
            .launchIn(lifecycleScope)

        Timber.d("Locale: ${localeManager.getLocale()}")
        Timber.d("Available Locales: ${localeManager.getAvailableLocales()}")
    }
}