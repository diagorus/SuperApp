package com.lordnikius.superapp.routine.exercise

import com.lordnikius.superapp.routine.exercise.step.PreparationStep
import com.lordnikius.superapp.routine.exercise.step.RelaxStep
import com.lordnikius.superapp.routine.exercise.step.StretchStep
import com.lordnikius.superapp.util.BeepToneManager
import com.lordnikius.superapp.util.TextToSpeechManager
import com.lordnikius.superapp.R
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class HandsExercise @Inject constructor(
    beepToneManager: BeepToneManager,
    textToSpeechManager: TextToSpeechManager,
): StretchingExercise(
    nameRes = R.string.hands,
    steps = flowOf(
        PreparationStep("Hands", PREPARATION_DURATION, textToSpeechManager),
        StretchStep(beepToneManager),
        RelaxStep(textToSpeechManager, beepToneManager),
        StretchStep(beepToneManager),
        RelaxStep(textToSpeechManager, beepToneManager),
        StretchStep(beepToneManager),
    )
) {

    companion object {
        private const val PREPARATION_DURATION = 10
    }
}