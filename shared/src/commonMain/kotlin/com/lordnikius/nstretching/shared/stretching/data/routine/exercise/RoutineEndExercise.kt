package com.lordnikius.nstretching.shared.stretching.data.routine.exercise

import com.lordnikius.nstretching.shared.stretching.data.routine.exercise.step.RoutineEndStep
import kotlinx.coroutines.flow.flowOf
import org.koin.core.annotation.Factory
import com.lordnikius.nstretching.shared.generated.resources.Res
import com.lordnikius.nstretching.shared.generated.resources.end

@Factory
class RoutineEndExercise : StretchingExercise(
    nameRes = Res.string.end,
    steps = flowOf(
        RoutineEndStep(),
    ),
)