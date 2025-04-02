package com.lordnikius.superapp.routine.exercise.step

import android.speech.tts.TextToSpeech
import com.lordnikius.superapp.util.TextToSpeechManager
import com.lordnikius.superapp.R
import kotlinx.coroutines.delay
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class PreparationStep(
    private val exerciseName: String,
    private val durationInSeconds: Double,
    private val textToSpeechManager: TextToSpeechManager,
) : StretchingExerciseStep(R.string.preparation) {

    override suspend fun step() {
        //todo, maybe to view
        textToSpeechManager.speak("$exerciseName, get ready!", TextToSpeech.QUEUE_FLUSH)
        delay(durationInSeconds.toDuration(DurationUnit.SECONDS))
    }
}