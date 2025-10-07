package com.lordnikius.superapp.shared.stretching.routine.exercise.step.elements

import com.lordnikius.superapp.shared.util.locale.StringUiData
import kotlin.time.DurationUnit
import kotlin.time.toDuration

sealed interface StepAction {

    class Beep : StepAction

    class Wait(durationInSeconds: Double) : StepAction {
        val duration = durationInSeconds.toDuration(DurationUnit.SECONDS)
    }

    class Say(val text: StringUiData) : StepAction

    class DoubleBeep : StepAction
}