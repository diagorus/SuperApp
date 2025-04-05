package com.lordnikius.superapp.routine.exercise

import com.lordnikius.superapp.routine.exercise.step.ChangeStep
import com.lordnikius.superapp.routine.exercise.step.PreparationStep
import com.lordnikius.superapp.routine.exercise.step.RelaxStep
import com.lordnikius.superapp.routine.exercise.step.StretchStep
import com.lordnikius.superapp.util.BeepToneManager
import com.lordnikius.superapp.util.textToSpeech.TextToSpeechManager
import com.lordnikius.superapp.R
import com.lordnikius.superapp.routine.exercise.step.BUTT_AND_BACK_PREPARATION_STEP_DURATION
import com.lordnikius.superapp.routine.exercise.step.CHANGE_TO_BACK_PREPARATION_STEP_DURATION
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class ButtAndBackExercise : StretchingExercise(
    nameRes = R.string.butt_and_back,
    steps = flowOf(
        PreparationStep(R.string.butt_and_back, BUTT_AND_BACK_PREPARATION_STEP_DURATION),
        StretchStep(),
        ChangeStep(),
        StretchStep(),
        PreparationStep(R.string.back, CHANGE_TO_BACK_PREPARATION_STEP_DURATION),
        StretchStep(),
        RelaxStep(),
        StretchStep(),
        ChangeStep(),
        StretchStep(),
        PreparationStep(R.string.back, CHANGE_TO_BACK_PREPARATION_STEP_DURATION),
        StretchStep(),
        RelaxStep(),
        StretchStep(),
        ChangeStep(),
        StretchStep(),
        PreparationStep(R.string.back, CHANGE_TO_BACK_PREPARATION_STEP_DURATION),
        StretchStep(),
    ),
)