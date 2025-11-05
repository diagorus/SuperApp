package com.lordnikius.nstretching.shared.stretching.data.routine.exercise.step

import com.lordnikius.nstretching.shared.stretching.data.routine.exercise.step.elements.StepAction
import com.lordnikius.nstretching.shared.util.locale.StringUiData
import kotlinx.coroutines.flow.flowOf
import org.jetbrains.compose.resources.StringResource
import com.lordnikius.nstretching.shared.generated.resources.Res
import com.lordnikius.nstretching.shared.generated.resources.exercise_get_ready
import com.lordnikius.nstretching.shared.generated.resources.preparation

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