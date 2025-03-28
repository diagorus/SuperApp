package com.lordnikius.superapp.routine.exercise.step

import com.lordnikius.superapp.util.BeepToneManager
import com.lordnikius.superapp.R
import kotlinx.coroutines.delay
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class StretchStep(
    private val beepToneManager: BeepToneManager,
): StretchingExerciseStep(R.string.stretch) {

    override suspend fun step() {
        beepToneManager.playSingleBeepTone()
        delay(DURATION.toDuration(DurationUnit.SECONDS))
    }

    companion object {
        const val DURATION = 30
    }
}