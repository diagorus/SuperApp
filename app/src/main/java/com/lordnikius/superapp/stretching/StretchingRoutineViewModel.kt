package com.lordnikius.superapp.stretching

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lordnikius.superapp.routine.Routine
import com.lordnikius.superapp.util.StringUiData
import com.lordnikius.superapp.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StretchingRoutineViewModel @Inject constructor(
    private val routine: Routine,
) : ViewModel() {

    private val _uiState = MutableStateFlow(StretchingRoutineUiState())
    val uiState = _uiState.asStateFlow()

    init {
        routine.currentExercise
            .onEach { exercise ->
                exercise.currentStep
                    .onEach { step ->
                        _uiState.update {
                            val stepNameRes = step?.nameRes
                            val stepName = stepNameRes?.let {
                                StringUiData.Argument.StringResource(step.nameRes)
                            } ?: StringUiData.Argument.StringValue("")
                            it.copy(
                                exerciseAndStep = StringUiData.Resource(
                                    id = R.string.hyphen_divider,
                                    arguments = arrayOf(
                                        StringUiData.Argument.StringResource(exercise.nameRes),
                                        stepName,
                                    )
                                ),
                            )
                        }
                    }
                    .launchIn(viewModelScope)
            }
            .launchIn(viewModelScope)
    }

    fun onStartClick() {
        viewModelScope.launch {
            routine.start()
        }
    }
}