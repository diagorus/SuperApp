package com.lordnikius.nstretching.shared.stretching.data.routine.exercise.step

import com.lordnikius.nstretching.shared.stretching.data.routine.exercise.step.elements.StepAction
import com.lordnikius.nstretching.shared.util.config.StretchingConfig
import com.lordnikius.nstretching.shared.util.locale.StringUiData
import kotlinx.coroutines.flow.flowOf
import com.lordnikius.nstretching.shared.generated.resources.Res
import com.lordnikius.nstretching.shared.generated.resources.relax

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