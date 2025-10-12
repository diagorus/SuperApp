package com.lordnikius.superapp.shared.stretching.data.routine.exercise.step

import com.lordnikius.superapp.shared.stretching.data.routine.exercise.step.elements.StepAction
import com.lordnikius.superapp.shared.util.config.StretchingConfig
import com.lordnikius.superapp.shared.util.locale.StringUiData
import kotlinx.coroutines.flow.flowOf
import superapp.shared.generated.resources.Res
import superapp.shared.generated.resources.relax

class RelaxStep(
    config: StretchingConfig,
) : StretchingExerciseStep(
    nameRes = Res.string.relax,
    actions = flowOf(
        StepAction.Say(StringUiData.Resource(Res.string.relax)),
        StepAction.Wait(config.relaxStepSecondsHalfDuration),
        StepAction.DoubleBeep(),
        StepAction.Wait(config.relaxStepSecondsHalfDuration),
    )
)