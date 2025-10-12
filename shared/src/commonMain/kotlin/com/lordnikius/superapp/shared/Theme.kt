package com.lordnikius.superapp.shared

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lordnikius.superapp.shared.stretching.ui.StretchingRoutineRoute
import com.lordnikius.superapp.shared.stretching.ui.StretchingRoutineScreen
import com.lordnikius.superapp.shared.stretching.ui.viewModel.StretchingRoutineViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {
    MaterialTheme {
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

