package com.lordnikius.superapp.stretching

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lordnikius.superapp.R
import com.lordnikius.superapp.routine.exercise.BackTwistsExercise
import com.lordnikius.superapp.routine.exercise.ButtAndBackExercise
import com.lordnikius.superapp.routine.exercise.HandsExercise
import com.lordnikius.superapp.routine.exercise.LongitudinalTwineExercise
import com.lordnikius.superapp.routine.exercise.QuadsExercise
import com.lordnikius.superapp.routine.exercise.RoutineEndExercise
import com.lordnikius.superapp.routine.exercise.TransverseTwineExercise
import com.lordnikius.superapp.routine.exercise.step.elements.StepElement
import com.lordnikius.superapp.util.BeepToneManager
import com.lordnikius.superapp.util.locale.LocaleManager
import com.lordnikius.superapp.util.StringUiData
import com.lordnikius.superapp.util.textToSpeech.TextToSpeechManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.concatMap
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class StretchingRoutineViewModel @Inject constructor(
    private val beepToneManager: BeepToneManager,
    private val textToSpeechManager: TextToSpeechManager,
) : ViewModel() {

    private val _uiState = MutableStateFlow(StretchingRoutineUiState())
    val uiState = _uiState.asStateFlow()

    private val exercises = flowOf(
        HandsExercise(),
        BackTwistsExercise(),
        LongitudinalTwineExercise(),
        TransverseTwineExercise(),
        QuadsExercise(),
        ButtAndBackExercise(),
        RoutineEndExercise(),
    )

    fun onStartClick() {
        exercises
            .onEach { exercise ->
                _uiState.update { it.copy(exercise = StringUiData.Resource(id = exercise.nameRes)) }
            }
            .flatMapConcat { it.steps }
            .onEach { step ->
                _uiState.update { it.copy(step = StringUiData.Resource(id = step.nameRes)) }
            }
            .flatMapConcat { it.actions }
            .onEach { action ->
                when (action) {
                    is StepElement.Say -> {
                        textToSpeechManager.speak(action.text)
                    }
                    is StepElement.Wait -> {
                        delay(action.duration)
                    }
                    is StepElement.Beep -> {
                        beepToneManager.playSingleBeepTone()
                    }
                    is StepElement.DoubleBeep -> {
                        beepToneManager.playDoubleBeepTone()
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    fun onMessageSaid() {
        _uiState.update { it.copy(messageToSay = StringUiData.Empty) }
    }
}