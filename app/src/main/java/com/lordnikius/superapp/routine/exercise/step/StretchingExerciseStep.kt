package com.lordnikius.superapp.routine.exercise.step

import androidx.annotation.StringRes

abstract class StretchingExerciseStep(
    @StringRes
    val nameRes: Int
) {

    abstract suspend fun step()
}