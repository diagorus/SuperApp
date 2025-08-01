package com.lordnikius.superapp.data.stretching.routine.exercise.step

import com.lordnikius.superapp.R
import com.lordnikius.superapp.routine.exercise.step.CHANGE_STEP_SECONDS_DURATION
import com.lordnikius.superapp.data.stretching.routine.exercise.step.elements.StepElement
import com.lordnikius.superapp.data.util.locale.StringUiData
import kotlinx.coroutines.flow.flowOf

class ChangeStep : StretchingExerciseStep(
    nameRes = R.string.change,
    actions = flowOf(
        StepElement.Say(StringUiData.Resource(R.string.change)),
        StepElement.Wait(CHANGE_STEP_SECONDS_DURATION),
    ),
)