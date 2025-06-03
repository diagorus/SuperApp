package com.lordnikius.superapp.stretching.routine.exercise.step

import com.lordnikius.superapp.R
import com.lordnikius.superapp.routine.exercise.step.STRETCH_STEP_SECONDS_DURATION
import com.lordnikius.superapp.stretching.routine.exercise.step.elements.StepElement
import kotlinx.coroutines.flow.flowOf

class StretchStep : StretchingExerciseStep(
    nameRes = R.string.stretch,
    actions = flowOf(
        StepElement.Beep(),
        StepElement.Wait(STRETCH_STEP_SECONDS_DURATION),
    ),
)