package com.lordnikius.superapp.data.stretching.routine.exercise

import com.lordnikius.superapp.data.stretching.routine.exercise.step.RoutineEndStep
import com.lordnikius.superapp.R
import kotlinx.coroutines.flow.flowOf

class RoutineEndExercise : StretchingExercise(
    nameRes = R.string.end,
    steps = flowOf(
        RoutineEndStep(),
    ),
)