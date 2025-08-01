package com.lordnikius.superapp.data.stretching.routine.exercise

import com.lordnikius.superapp.data.stretching.routine.exercise.step.PreparationStep
import com.lordnikius.superapp.data.stretching.routine.exercise.step.RelaxStep
import com.lordnikius.superapp.data.stretching.routine.exercise.step.StretchStep
import com.lordnikius.superapp.R
import com.lordnikius.superapp.routine.exercise.step.HANDS_PREPARATION_STEP_DURATION
import kotlinx.coroutines.flow.flowOf

class HandsExercise : StretchingExercise(
    nameRes = R.string.hands,
    steps = flowOf(
        PreparationStep(R.string.hands, HANDS_PREPARATION_STEP_DURATION),
        StretchStep(),
        RelaxStep(),
        StretchStep(),
        RelaxStep(),
        StretchStep(),
    )
)