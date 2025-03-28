package com.lordnikius.superapp.routine.exercise

import com.lordnikius.superapp.routine.exercise.step.RoutineEndStep
import com.lordnikius.superapp.util.TextToSpeechManager
import com.lordnikius.superapp.R
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class RoutineEndExercise @Inject constructor(
    textToSpeechManager: TextToSpeechManager,
): StretchingExercise(
    nameRes = R.string.end,
    steps = flowOf(
        RoutineEndStep(textToSpeechManager)
    )
)