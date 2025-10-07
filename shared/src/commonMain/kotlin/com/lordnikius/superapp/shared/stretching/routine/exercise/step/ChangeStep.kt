package com.lordnikius.superapp.shared.stretching.routine.exercise.step

import com.lordnikius.superapp.shared.stretching.routine.exercise.step.elements.StepAction
import com.lordnikius.superapp.shared.util.config.StretchingConfig
import com.lordnikius.superapp.shared.util.locale.StringUiData
import kotlinx.coroutines.flow.flowOf
import superapp.shared.generated.resources.Res
import superapp.shared.generated.resources.change

class ChangeStep(
    config: StretchingConfig,
) : StretchingExerciseStep(
    nameRes = Res.string.change,
    actions = flowOf(
        StepAction.Say(StringUiData.Resource(Res.string.change)),
        StepAction.Wait(config.changeStepSecondsDuration),
    ),
)