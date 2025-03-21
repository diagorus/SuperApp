package com.lordnikius.superapp.stretching

import com.lordnikius.superapp.util.StringUiData

data class StretchingRoutineUiState(
    val exercise: StringUiData = StringUiData.Empty,
    val step: StringUiData = StringUiData.Empty,
)