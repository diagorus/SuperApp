package com.lordnikius.superapp.stretching.routine.exercise.step

import com.lordnikius.superapp.R
import com.lordnikius.superapp.stretching.routine.exercise.step.elements.StepElement
import com.lordnikius.superapp.util.locale.StringUiData
import kotlinx.coroutines.flow.flowOf

class RoutineEndStep : StretchingExerciseStep(
    nameRes = R.string.end,
    actions = flowOf(
        StepElement.Say(StringUiData.Resource(R.string.congratulations_routine_end)),
    ),
)