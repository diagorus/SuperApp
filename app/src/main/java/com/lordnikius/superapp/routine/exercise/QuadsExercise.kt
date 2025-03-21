package com.lordnikius.superapp.routine.exercise

import com.lordnikius.superapp.routine.exercise.step.ChangeStep
import com.lordnikius.superapp.routine.exercise.step.PreparationStep
import com.lordnikius.superapp.routine.exercise.step.StretchStep
import com.lordnikius.superapp.util.BeepToneManager
import com.lordnikius.superapp.util.TextToSpeechManager
import com.lordnikius.superapp.R
import javax.inject.Inject

class QuadsExercise @Inject constructor(
    beepToneManager: BeepToneManager,
    textToSpeechManager: TextToSpeechManager,
) : StretchingExercise(
    nameRes = R.string.quads,
    steps = listOf(
        PreparationStep("Quads", PREPARATION_DURATION, textToSpeechManager),
        StretchStep(beepToneManager),
        ChangeStep(textToSpeechManager),
        StretchStep(beepToneManager),
        ChangeStep(textToSpeechManager),
        StretchStep(beepToneManager),
        ChangeStep(textToSpeechManager),
        StretchStep(beepToneManager),
        ChangeStep(textToSpeechManager),
        StretchStep(beepToneManager),
        ChangeStep(textToSpeechManager),
        StretchStep(beepToneManager),
    ),
) {

    companion object {
        private const val PREPARATION_DURATION = 10
    }
}