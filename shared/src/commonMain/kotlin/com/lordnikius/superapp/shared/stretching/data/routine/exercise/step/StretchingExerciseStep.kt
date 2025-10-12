package com.lordnikius.superapp.shared.stretching.data.routine.exercise.step

import com.lordnikius.superapp.shared.stretching.data.routine.exercise.step.elements.StepAction
import kotlinx.coroutines.flow.Flow
import org.jetbrains.compose.resources.StringResource

abstract class StretchingExerciseStep(
    val nameRes: StringResource,
    val actions: Flow<StepAction>
)