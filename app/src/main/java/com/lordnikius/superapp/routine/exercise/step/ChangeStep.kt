package com.lordnikius.superapp.routine.exercise.step

import android.speech.tts.TextToSpeech
import com.lordnikius.superapp.util.TextToSpeechManager
import com.lordnikius.superapp.R
import kotlinx.coroutines.delay
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class ChangeStep(
    private val textToSpeechManager: TextToSpeechManager,
): StretchingExerciseStep(R.string.change) {

    override suspend fun step() {
        textToSpeechManager.speak("Change!", TextToSpeech.QUEUE_FLUSH)
        delay(DURATION.toDuration(DurationUnit.SECONDS))
    }

    companion object {
        const val DURATION = 5
    }
}