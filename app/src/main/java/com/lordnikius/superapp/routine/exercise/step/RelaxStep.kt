package com.lordnikius.superapp.routine.exercise.step

import android.speech.tts.TextToSpeech
import com.lordnikius.superapp.util.BeepToneManager
import com.lordnikius.superapp.util.TextToSpeechManager
import com.lordnikius.superapp.R
import kotlinx.coroutines.delay
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class RelaxStep(
    private val textToSpeechManager: TextToSpeechManager,
    private val beepToneManager: BeepToneManager,
): StretchingExerciseStep(R.string.relax) {

    override suspend fun step() {
        textToSpeechManager.speak("Relax!", TextToSpeech.QUEUE_FLUSH)
        delay(RELAX_STEP_SECONDS_HALF_DURATION.toDuration(DurationUnit.SECONDS))
        beepToneManager.playDoubleBeepTone()
        delay(RELAX_STEP_SECONDS_HALF_DURATION.toDuration(DurationUnit.SECONDS))
    }
}