package com.lordnikius.superapp.shared.stretching.data.routine.exercise

import com.lordnikius.superapp.shared.stretching.data.routine.exercise.step.StretchingExerciseStep
import kotlinx.coroutines.flow.Flow
import org.jetbrains.compose.resources.StringResource

abstract class StretchingExercise(
    val nameRes: StringResource,
    val steps: Flow<StretchingExerciseStep>,
)