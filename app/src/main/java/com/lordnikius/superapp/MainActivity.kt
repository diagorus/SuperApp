package com.lordnikius.superapp

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.LocaleManagerCompat
import com.lordnikius.superapp.stretching.StretchingRoutineScreen
import com.lordnikius.superapp.stretching.StretchingRoutineViewModel
import com.lordnikius.superapp.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

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

        val locales = LocaleManagerCompat.getSystemLocales(this).toLanguageTags().split(',')
        Timber.e("Locales: $locales")
    }
}