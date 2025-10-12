package com.lordnikius.superapp.shared.stretching.data.routine.exercise.step

import com.lordnikius.superapp.shared.stretching.data.routine.exercise.step.elements.StepAction
import com.lordnikius.superapp.shared.util.locale.StringUiData
import kotlinx.coroutines.flow.flowOf
import superapp.shared.generated.resources.Res
import superapp.shared.generated.resources.congratulations_routine_end
import superapp.shared.generated.resources.end

class RoutineEndStep : StretchingExerciseStep(
    nameRes = Res.string.end,
    actions = flowOf(
        StepAction.Say(StringUiData.Resource(Res.string.congratulations_routine_end)),
    ),
)