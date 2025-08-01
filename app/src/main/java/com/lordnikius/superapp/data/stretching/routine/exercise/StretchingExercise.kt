package com.lordnikius.superapp.data.stretching.routine.exercise

import androidx.annotation.StringRes
import com.lordnikius.superapp.data.stretching.routine.exercise.step.StretchingExerciseStep
import kotlinx.coroutines.flow.Flow

abstract class StretchingExercise(
    @get:StringRes
    val nameRes: Int,
    val steps: Flow<StretchingExerciseStep>,
)