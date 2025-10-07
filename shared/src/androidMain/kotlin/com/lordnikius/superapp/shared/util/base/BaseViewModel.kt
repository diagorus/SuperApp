package com.lordnikius.superapp.shared.util.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

open class BaseViewModel<UiState : BaseUiState>(
    initialUiState: UiState,
) : ViewModel() {

    private val _uiState = MutableStateFlow(initialUiState)
    val uiState = _uiState.asStateFlow()

    protected fun updateUiState(mutation: UiState.() -> UiState) {
        _uiState.update { it.mutation() }
    }
}