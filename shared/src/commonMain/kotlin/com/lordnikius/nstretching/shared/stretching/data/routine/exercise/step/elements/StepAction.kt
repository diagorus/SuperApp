package com.lordnikius.nstretching.shared.stretching.data.routine.exercise.step.elements

import com.lordnikius.nstretching.shared.util.locale.StringUiData
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