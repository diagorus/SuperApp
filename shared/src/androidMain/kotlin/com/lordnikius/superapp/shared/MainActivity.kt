package com.lordnikius.superapp.shared

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lordnikius.superapp.shared.stretching.ui.StretchingRoutineRoute
import com.lordnikius.superapp.shared.stretching.ui.StretchingRoutineScreen
import com.lordnikius.superapp.shared.stretching.ui.viewModel.StretchingRoutineViewModel
import org.koin.compose.viewmodel.koinViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App {
                NavHost(
                    navController = rememberNavController(),
                    startDestination = StretchingRoutineRoute,
                ) {
                    composable<StretchingRoutineRoute> {
                        val viewModel = koinViewModel<StretchingRoutineViewModel>()
                        StretchingRoutineScreen(viewModel)
                    }
                }
            }
        }
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }
}