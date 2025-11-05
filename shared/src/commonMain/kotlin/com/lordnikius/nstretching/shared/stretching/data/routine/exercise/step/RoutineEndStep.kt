package com.lordnikius.nstretching.shared.stretching.data.routine.exercise.step

import com.lordnikius.nstretching.shared.stretching.data.routine.exercise.step.elements.StepAction
import com.lordnikius.nstretching.shared.util.locale.StringUiData
import kotlinx.coroutines.flow.flowOf
import com.lordnikius.nstretching.shared.generated.resources.Res
import com.lordnikius.nstretching.shared.generated.resources.congratulations_routine_end
import com.lordnikius.nstretching.shared.generated.resources.end

class RoutineEndStep : StretchingExerciseStep(
    nameRes = Res.string.end,
    actions = flowOf(
        StepAction.Say(StringUiData.Resource(Res.string.congratulations_routine_end)),
    ),
)