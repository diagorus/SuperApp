package com.lordnikius.superapp.routine.exercise.step

import android.speech.tts.TextToSpeech
import com.lordnikius.superapp.util.textToSpeech.TextToSpeechManager
import com.lordnikius.superapp.R
import com.lordnikius.superapp.routine.exercise.step.elements.StepElement
import com.lordnikius.superapp.util.StringUiData
import kotlinx.coroutines.flow.flowOf

class RoutineEndStep : StretchingExerciseStep(
    nameRes = R.string.end,
    actions = flowOf(
        StepElement.Say(StringUiData.Resource(R.string.end)),
    ),
)