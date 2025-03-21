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
        delay(HALF_DURATION.toDuration(DurationUnit.SECONDS))
        beepToneManager.playDoubleBeepTone()
        delay(HALF_DURATION.toDuration(DurationUnit.SECONDS))
    }

    companion object {
        const val HALF_DURATION = 5
    }
}