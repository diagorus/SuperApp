package com.lordnikius.superapp.routine.exercise.step

import android.speech.tts.TextToSpeech
import androidx.annotation.StringRes
import com.lordnikius.superapp.util.textToSpeech.TextToSpeechManager
import com.lordnikius.superapp.R
import com.lordnikius.superapp.routine.exercise.step.elements.StepElement
import com.lordnikius.superapp.util.StringUiData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class PreparationStep(
    @StringRes exerciseNameRes: Int,
    durationInSeconds: Double,
) : StretchingExerciseStep(
    nameRes = R.string.preparation,
    actions = flowOf(
        StepElement.Say(
            StringUiData.Resource(
                R.string.exercise_get_ready,
                arrayOf(StringUiData.Argument.StringResource(exerciseNameRes)),
            )
        ),
        StepElement.Wait(durationInSeconds),
    )
)