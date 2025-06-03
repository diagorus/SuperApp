package com.lordnikius.superapp.stretching.routine.exercise.step

import com.lordnikius.superapp.R
import com.lordnikius.superapp.routine.exercise.step.RELAX_STEP_SECONDS_HALF_DURATION
import com.lordnikius.superapp.stretching.routine.exercise.step.elements.StepElement
import com.lordnikius.superapp.util.locale.StringUiData
import kotlinx.coroutines.flow.flowOf

class RelaxStep : StretchingExerciseStep(
    nameRes = R.string.relax,
    actions = flowOf(
        StepElement.Say(StringUiData.Resource(R.string.relax)),
        StepElement.Wait(RELAX_STEP_SECONDS_HALF_DURATION),
        StepElement.DoubleBeep(),
        StepElement.Wait(RELAX_STEP_SECONDS_HALF_DURATION),
    )
)