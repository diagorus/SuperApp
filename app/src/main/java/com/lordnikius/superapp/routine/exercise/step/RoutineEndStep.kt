package com.lordnikius.superapp.routine.exercise.step

import android.speech.tts.TextToSpeech
import com.lordnikius.superapp.util.TextToSpeechManager
import com.lordnikius.superapp.R

class RoutineEndStep(
    private val textToSpeechManager: TextToSpeechManager,
) : StretchingExerciseStep(R.string.end) {

    override suspend fun step() {
        //todo, maybe to view
        textToSpeechManager.speak("Congratulations! Now, life continues!", TextToSpeech.QUEUE_FLUSH)
    }
}