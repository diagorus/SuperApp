package com.lordnikius.superapp.routine.exercise

import com.lordnikius.superapp.routine.exercise.step.ChangeStep
import com.lordnikius.superapp.routine.exercise.step.PreparationStep
import com.lordnikius.superapp.routine.exercise.step.RelaxStep
import com.lordnikius.superapp.routine.exercise.step.StretchStep
import com.lordnikius.superapp.util.BeepToneManager
import com.lordnikius.superapp.util.TextToSpeechManager
import com.lordnikius.superapp.R
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class ButtAndBackExercise @Inject constructor(
    beepToneManager: BeepToneManager,
    textToSpeechManager: TextToSpeechManager,
) : StretchingExercise(
    nameRes = R.string.butt_and_back,
    steps = flowOf(
        PreparationStep("Butt and back", PREPARATION_DURATION, textToSpeechManager),
        StretchStep(beepToneManager),
        ChangeStep(textToSpeechManager),
        StretchStep(beepToneManager),
        PreparationStep("Back", CHANGE_TO_BACK_DURATION, textToSpeechManager),
        StretchStep(beepToneManager),
        RelaxStep(textToSpeechManager, beepToneManager),
        StretchStep(beepToneManager),
        ChangeStep(textToSpeechManager),
        StretchStep(beepToneManager),
        PreparationStep("Back", CHANGE_TO_BACK_DURATION, textToSpeechManager),
        StretchStep(beepToneManager),
        RelaxStep(textToSpeechManager, beepToneManager),
        StretchStep(beepToneManager),
        ChangeStep(textToSpeechManager),
        StretchStep(beepToneManager),
        PreparationStep("Back", CHANGE_TO_BACK_DURATION, textToSpeechManager),
        StretchStep(beepToneManager),
    ),
) {

    companion object {
        private const val PREPARATION_DURATION = 10
        private const val CHANGE_TO_BACK_DURATION = 5
    }
}