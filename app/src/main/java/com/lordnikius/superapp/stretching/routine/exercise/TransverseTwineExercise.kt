package com.lordnikius.superapp.stretching.routine.exercise

import com.lordnikius.superapp.stretching.routine.exercise.step.PreparationStep
import com.lordnikius.superapp.stretching.routine.exercise.step.RelaxStep
import com.lordnikius.superapp.stretching.routine.exercise.step.StretchStep
import com.lordnikius.superapp.R
import com.lordnikius.superapp.routine.exercise.step.TRANSVERSE_TWINE_PREPARATION_STEP_DURATION
import kotlinx.coroutines.flow.flowOf

class TransverseTwineExercise : StretchingExercise(
    nameRes = R.string.transverse_twine,
    steps = flowOf(
        PreparationStep(R.string.transverse_twine, TRANSVERSE_TWINE_PREPARATION_STEP_DURATION),
        StretchStep(),
        RelaxStep(),
        StretchStep(),
        RelaxStep(),
        StretchStep(),
    ),
)