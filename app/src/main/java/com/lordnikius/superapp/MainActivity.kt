package com.lordnikius.superapp

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lordnikius.superapp.shared.platform
import com.lordnikius.superapp.stretching.ui.StretchingRoutineRoute
import com.lordnikius.superapp.stretching.ui.StretchingRoutineScreen
import com.lordnikius.superapp.stretching.viewModel.StretchingRoutineViewModel
import com.lordnikius.superapp.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.i("Hello from shared module: " + (platform()))
        setContent {
            AppTheme {
                NavHost(
                    navController = rememberNavController(),
                    startDestination = StretchingRoutineRoute,
                ) {
                    composable<StretchingRoutineRoute> { backStackEntry ->
                        val viewModel = hiltViewModel<StretchingRoutineViewModel>(backStackEntry)
                        StretchingRoutineScreen(viewModel)
                    }
                }
            }
        }
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }
}