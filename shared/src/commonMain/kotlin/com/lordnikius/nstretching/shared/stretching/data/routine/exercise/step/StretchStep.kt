package com.lordnikius.nstretching.shared.stretching.data.routine.exercise.step

import com.lordnikius.nstretching.shared.stretching.data.routine.exercise.step.elements.StepAction
import com.lordnikius.nstretching.shared.util.config.StretchingConfig
import kotlinx.coroutines.flow.flowOf
import com.lordnikius.nstretching.shared.generated.resources.Res
import com.lordnikius.nstretching.shared.generated.resources.stretch

class StretchStep(
    config: StretchingConfig,
) : StretchingExerciseStep(
    nameRes = Res.string.stretch,
    actions = flowOf(
        StepAction.Beep(),
        StepAction.Wait(config.stretchStepSecondsDuration),
    ),
)