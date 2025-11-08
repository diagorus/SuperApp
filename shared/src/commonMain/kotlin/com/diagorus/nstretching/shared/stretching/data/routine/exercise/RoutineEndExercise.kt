package com.diagorus.nstretching.shared.stretching.data.routine.exercise

import com.diagorus.nstretching.shared.stretching.data.routine.exercise.step.RoutineEndStep
import kotlinx.coroutines.flow.flowOf
import org.koin.core.annotation.Factory
import com.diagorus.nstretching.shared.generated.resources.Res
import com.diagorus.nstretching.shared.generated.resources.end

@Factory
class RoutineEndExercise : StretchingExercise(
    nameRes = Res.string.end,
    steps = flowOf(
        RoutineEndStep(),
    ),
)