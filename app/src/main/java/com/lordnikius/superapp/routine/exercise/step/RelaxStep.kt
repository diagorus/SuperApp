package com.lordnikius.superapp.routine.exercise.step

import android.speech.tts.TextToSpeech
import com.lordnikius.superapp.util.BeepToneManager
import com.lordnikius.superapp.util.textToSpeech.TextToSpeechManager
import com.lordnikius.superapp.R
import com.lordnikius.superapp.routine.exercise.step.elements.StepElement
import com.lordnikius.superapp.util.StringUiData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class RelaxStep : StretchingExerciseStep(
    nameRes = R.string.relax,
    actions = flowOf(
        StepElement.Say(StringUiData.Resource(R.string.relax)),
        StepElement.Wait(RELAX_STEP_SECONDS_HALF_DURATION),
        StepElement.DoubleBeep(),
        StepElement.Wait(RELAX_STEP_SECONDS_HALF_DURATION),
    )
)