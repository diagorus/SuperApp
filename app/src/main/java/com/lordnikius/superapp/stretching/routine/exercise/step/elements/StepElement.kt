package com.lordnikius.superapp.stretching.routine.exercise.step.elements

import com.lordnikius.superapp.util.locale.StringUiData
import kotlin.time.DurationUnit
import kotlin.time.toDuration

sealed interface StepElement {

    class Beep : StepElement

    class Wait(durationInSeconds: Double) : StepElement {
        val duration = durationInSeconds.toDuration(DurationUnit.SECONDS)
    }

    class Say(val text: StringUiData) : StepElement

    class DoubleBeep : StepElement
}