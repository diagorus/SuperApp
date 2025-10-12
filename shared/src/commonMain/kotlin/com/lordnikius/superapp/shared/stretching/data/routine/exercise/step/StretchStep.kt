package com.lordnikius.superapp.shared.stretching.data.routine.exercise.step

import com.lordnikius.superapp.shared.stretching.data.routine.exercise.step.elements.StepAction
import com.lordnikius.superapp.shared.util.config.StretchingConfig
import kotlinx.coroutines.flow.flowOf
import superapp.shared.generated.resources.Res
import superapp.shared.generated.resources.stretch

class StretchStep(
    config: StretchingConfig,
) : StretchingExerciseStep(
    nameRes = Res.string.stretch,
    actions = flowOf(
        StepAction.Beep(),
        StepAction.Wait(config.stretchStepSecondsDuration),
    ),
)