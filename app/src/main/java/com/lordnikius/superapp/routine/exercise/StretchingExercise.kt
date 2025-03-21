package com.lordnikius.superapp.routine.exercise

import androidx.annotation.StringRes
import com.lordnikius.superapp.routine.exercise.step.StretchingExerciseStep
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.runningFold
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.update

abstract class StretchingExercise(
    @get:StringRes
    val nameRes: Int,
    val steps: Flow<StretchingExerciseStep>,
)