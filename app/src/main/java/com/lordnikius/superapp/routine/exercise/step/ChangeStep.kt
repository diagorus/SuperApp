package com.lordnikius.superapp.routine.exercise.step

import android.speech.tts.TextToSpeech
import com.lordnikius.superapp.util.textToSpeech.TextToSpeechManager
import com.lordnikius.superapp.R
import com.lordnikius.superapp.routine.exercise.step.elements.StepElement
import com.lordnikius.superapp.util.StringUiData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class ChangeStep : StretchingExerciseStep(
    nameRes = R.string.change,
    actions = flowOf(
        StepElement.Say(StringUiData.Resource(R.string.change)),
        StepElement.Wait(CHANGE_STEP_SECONDS_DURATION),
    ),
)