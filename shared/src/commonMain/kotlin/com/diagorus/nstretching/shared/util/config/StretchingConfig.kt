package com.diagorus.nstretching.shared.util.config

interface StretchingConfig {
    val backTwistsPreparationStepDuration: Double
    val buttAndBackPreparationStepDuration: Double
    val changeToBackPreparationStepDuration: Double
    val handsPreparationStepDuration: Double
    val longitudinalTwinePreparationStepDuration: Double
    val quadsPreparationStepDuration: Double
    val transverseTwinePreparationStepDuration: Double
    val changeStepSecondsDuration: Double
    val relaxStepSecondsHalfDuration: Double
    val stretchStepSecondsDuration: Double
}