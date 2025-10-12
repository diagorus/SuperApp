package com.lordnikius.superapp.shared.stretching.data.routine.exercise.step

import com.lordnikius.superapp.shared.stretching.data.routine.exercise.step.elements.StepAction
import com.lordnikius.superapp.shared.util.locale.StringUiData
import kotlinx.coroutines.flow.flowOf
import org.jetbrains.compose.resources.StringResource
import superapp.shared.generated.resources.Res
import superapp.shared.generated.resources.exercise_get_ready
import superapp.shared.generated.resources.preparation

class PreparationStep(
    exerciseNameRes: StringResource,
    durationInSeconds: Double,
) : StretchingExerciseStep(
    nameRes = Res.string.preparation,
    actions = flowOf(
        StepAction.Say(
            StringUiData.Resource(
                Res.string.exercise_get_ready,
                listOf(StringUiData.Argument.StringRes(exerciseNameRes)),
            )
        ),
        StepAction.Wait(durationInSeconds),
    )
)