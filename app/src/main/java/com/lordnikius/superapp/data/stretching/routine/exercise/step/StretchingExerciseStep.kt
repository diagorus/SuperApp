package com.lordnikius.superapp.data.stretching.routine.exercise.step

import androidx.annotation.StringRes
import com.lordnikius.superapp.data.stretching.routine.exercise.step.elements.StepElement
import kotlinx.coroutines.flow.Flow

abstract class StretchingExerciseStep(
    @StringRes
    val nameRes: Int,
    val actions: Flow<StepElement>
)