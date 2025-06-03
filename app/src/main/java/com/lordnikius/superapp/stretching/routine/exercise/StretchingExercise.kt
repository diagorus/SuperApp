package com.lordnikius.superapp.stretching.routine.exercise

import androidx.annotation.StringRes
import com.lordnikius.superapp.stretching.routine.exercise.step.StretchingExerciseStep
import kotlinx.coroutines.flow.Flow

abstract class StretchingExercise(
    @get:StringRes
    val nameRes: Int,
    val steps: Flow<StretchingExerciseStep>,
)