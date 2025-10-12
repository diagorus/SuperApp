package com.lordnikius.superapp.shared.stretching.data.routine.exercise

import com.lordnikius.superapp.shared.stretching.data.routine.exercise.step.RoutineEndStep
import kotlinx.coroutines.flow.flowOf
import org.koin.core.annotation.Factory
import superapp.shared.generated.resources.Res
import superapp.shared.generated.resources.end

@Factory
class RoutineEndExercise : StretchingExercise(
    nameRes = Res.string.end,
    steps = flowOf(
        RoutineEndStep(),
    ),
)