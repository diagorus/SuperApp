package com.lordnikius.superapp.stretching.routine.exercise

import com.lordnikius.superapp.stretching.routine.exercise.step.ChangeStep
import com.lordnikius.superapp.stretching.routine.exercise.step.PreparationStep
import com.lordnikius.superapp.stretching.routine.exercise.step.StretchStep
import com.lordnikius.superapp.R
import com.lordnikius.superapp.routine.exercise.step.QUADS_PREPARATION_STEP_DURATION
import kotlinx.coroutines.flow.flowOf

class QuadsExercise : StretchingExercise(
    nameRes = R.string.quads,
    steps = flowOf(
        PreparationStep(R.string.quads, QUADS_PREPARATION_STEP_DURATION),
        StretchStep(),
        ChangeStep(),
        StretchStep(),
        ChangeStep(),
        StretchStep(),
        ChangeStep(),
        StretchStep(),
        ChangeStep(),
        StretchStep(),
        ChangeStep(),
        StretchStep(),
    ),
)