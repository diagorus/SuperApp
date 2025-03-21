package com.lordnikius.superapp.stretching

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lordnikius.superapp.routine.Routine
import com.lordnikius.superapp.util.StringUiData
import com.lordnikius.superapp.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.concatMap
import kotlinx.coroutines.flow.flatMapConcat
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

    fun onStartClick() {
        routine.exercises
            .onEach { exercise ->
                _uiState.update { it.copy(exercise = StringUiData.Resource(id = exercise.nameRes)) }
            }
            .flatMapConcat { it.steps }
            .onEach { step ->
                _uiState.update { it.copy(step = StringUiData.Resource(id = step.nameRes)) }
                step.step()
            }
            .launchIn(viewModelScope)
    }
}