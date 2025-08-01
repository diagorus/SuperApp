package com.lordnikius.superapp.data.stretching.routine.exercise

import com.lordnikius.superapp.data.stretching.routine.exercise.step.ChangeStep
import com.lordnikius.superapp.data.stretching.routine.exercise.step.PreparationStep
import com.lordnikius.superapp.data.stretching.routine.exercise.step.StretchStep
import com.lordnikius.superapp.R
import com.lordnikius.superapp.routine.exercise.step.LONGITUDINAL_TWINE_PREPARATION_STEP_DURATION
import kotlinx.coroutines.flow.flowOf

class LongitudinalTwineExercise : StretchingExercise(
    nameRes = R.string.longitudinal_twine,
    steps = flowOf(
        PreparationStep(R.string.longitudinal_twine, LONGITUDINAL_TWINE_PREPARATION_STEP_DURATION),
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