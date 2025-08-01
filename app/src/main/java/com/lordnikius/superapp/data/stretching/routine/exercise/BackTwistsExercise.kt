package com.lordnikius.superapp.data.stretching.routine.exercise

import com.lordnikius.superapp.R
import com.lordnikius.superapp.routine.exercise.step.BACK_TWISTS_PREPARATION_STEP_DURATION
import com.lordnikius.superapp.data.stretching.routine.exercise.step.StretchStep
import com.lordnikius.superapp.data.stretching.routine.exercise.step.ChangeStep
import com.lordnikius.superapp.data.stretching.routine.exercise.step.PreparationStep
import kotlinx.coroutines.flow.flowOf

class BackTwistsExercise : StretchingExercise(
    nameRes = R.string.back_twists,
    steps = flowOf(
        PreparationStep(R.string.back_twists, BACK_TWISTS_PREPARATION_STEP_DURATION),
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