package com.lordnikius.superapp.routine.exercise

import com.lordnikius.superapp.R
import com.lordnikius.superapp.routine.exercise.step.BACK_TWISTS_PREPARATION_STEP_DURATION
import com.lordnikius.superapp.routine.exercise.step.StretchStep
import com.lordnikius.superapp.routine.exercise.step.ChangeStep
import com.lordnikius.superapp.routine.exercise.step.PreparationStep
import com.lordnikius.superapp.util.BeepToneManager
import com.lordnikius.superapp.util.TextToSpeechManager
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class BackTwistsExercise @Inject constructor(
    beepToneManager: BeepToneManager,
    textToSpeechManager: TextToSpeechManager,
) : StretchingExercise(
    nameRes = R.string.back_twists,
    steps = flowOf(
        PreparationStep("Back twists", BACK_TWISTS_PREPARATION_STEP_DURATION, textToSpeechManager),
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
)