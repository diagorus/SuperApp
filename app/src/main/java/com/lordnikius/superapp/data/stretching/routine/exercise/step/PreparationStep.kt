package com.lordnikius.superapp.data.stretching.routine.exercise.step

import androidx.annotation.StringRes
import com.lordnikius.superapp.R
import com.lordnikius.superapp.data.stretching.routine.exercise.step.elements.StepElement
import com.lordnikius.superapp.data.util.locale.StringUiData
import kotlinx.coroutines.flow.flowOf

class PreparationStep(
    @StringRes exerciseNameRes: Int,
    durationInSeconds: Double,
) : StretchingExerciseStep(
    nameRes = R.string.preparation,
    actions = flowOf(
        StepElement.Say(
            StringUiData.Resource(
                R.string.exercise_get_ready,
                arrayOf(StringUiData.Argument.StringResource(exerciseNameRes)),
            )
        ),
        StepElement.Wait(durationInSeconds),
    )
)