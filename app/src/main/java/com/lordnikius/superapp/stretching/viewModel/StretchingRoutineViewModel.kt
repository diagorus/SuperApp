package com.lordnikius.superapp.stretching.viewModel

import androidx.lifecycle.viewModelScope
import com.lordnikius.superapp.data.stretching.routine.exercise.BackTwistsExercise
import com.lordnikius.superapp.data.stretching.routine.exercise.ButtAndBackExercise
import com.lordnikius.superapp.data.stretching.routine.exercise.HandsExercise
import com.lordnikius.superapp.data.stretching.routine.exercise.LongitudinalTwineExercise
import com.lordnikius.superapp.data.stretching.routine.exercise.QuadsExercise
import com.lordnikius.superapp.data.stretching.routine.exercise.RoutineEndExercise
import com.lordnikius.superapp.data.stretching.routine.exercise.TransverseTwineExercise
import com.lordnikius.superapp.data.stretching.routine.exercise.step.elements.StepElement
import com.lordnikius.superapp.stretching.SupportedLocaleWithTextToSpeechAvailability
import com.lordnikius.superapp.util.BeepToneManager
import com.lordnikius.superapp.util.base.BaseViewModel
import com.lordnikius.superapp.util.coroutines.launchInPausing
import com.lordnikius.superapp.util.locale.LocaleManager
import com.lordnikius.superapp.data.util.locale.StringUiData
import com.lordnikius.superapp.util.textToSpeech.TextToSpeechEngine
import com.lordnikius.superapp.util.textToSpeech.TextToSpeechManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.koitharu.pausingcoroutinedispatcher.PausingJob
import javax.inject.Inject

@HiltViewModel
class StretchingRoutineViewModel @Inject constructor(
    private val beepToneManager: BeepToneManager,
    private val textToSpeechManager: TextToSpeechManager,
    private val localeManager: LocaleManager,
) : BaseViewModel<StretchingRoutineUiState>(StretchingRoutineUiState()) {

    private var routineJob: PausingJob? = null

    private val routineExercises = flowOf(
        HandsExercise(),
        BackTwistsExercise(),
        LongitudinalTwineExercise(),
        TransverseTwineExercise(),
        QuadsExercise(),
        ButtAndBackExercise(),
        RoutineEndExercise(),
    )

    init {
        fetchLocaleAvailability()
        fetchTextToSpeechEngines()
    }

    private fun fetchLocaleAvailability() {
        viewModelScope.launch {
            val supportedLocales = localeManager.supportedLocales
            val supportedLocalesWithTextToSpeechAvailability = supportedLocales.map {
                val isTextToSpeechAvailable = textToSpeechManager.isLanguageAvailable(it)
                SupportedLocaleWithTextToSpeechAvailability(it, isTextToSpeechAvailable)
            }
            val currentLocale = localeManager.getCurrentLocale()
            val currentLocaleWithTextToSpeechAvailability =
                supportedLocalesWithTextToSpeechAvailability.firstOrNull { it.supportedLocale == currentLocale }
            updateUiState {
                copy(
                    supportedLocales = supportedLocalesWithTextToSpeechAvailability,
                    currentLocale = currentLocaleWithTextToSpeechAvailability,
                )
            }
        }
    }

    private fun fetchTextToSpeechEngines() {
        viewModelScope.launch {
            val currentEngine = textToSpeechManager.getCurrentEngine()
            val engines = textToSpeechManager.getEngines()
            updateUiState {
                copy(
                    currentTextToSpeechEngine = currentEngine,
                    textToSpeechEngines = engines,
                )
            }
        }
    }

    fun onStartPauseClick() {
        if (uiState.value.currentLocale?.isTextToSpeechAvailable == true) {
            startOrPause()
        } else {
            updateUiState { copy(showVoiceUnavailableDialog = ShowVoiceUnavailableDialog) }
        }
    }

    private fun startOrPause() {
        val isRunning = uiState.value.state == StretchingRoutineState.RUNNING
        if (isRunning) {
            pause()
        } else {
            if (routineJob == null) {
                start()
            } else {
                routineJob?.resume()
                updateUiState { copy(state = StretchingRoutineState.RUNNING) }
            }
        }
    }

    private fun start() {
        routineJob = routineExercises
            .onEach { exercise ->
                updateUiState { copy(exercise = StringUiData.Resource(id = exercise.nameRes)) }
            }
            .flatMapConcat { it.steps }
            .onEach { step ->
                updateUiState { copy(step = StringUiData.Resource(id = step.nameRes)) }
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
            .onStart {
                updateUiState { copy(state = StretchingRoutineState.RUNNING) }
            }
            .onCompletion {
                updateUiState { copy(state = StretchingRoutineState.IDLE) }
            }
            .launchInPausing(viewModelScope)
    }

    private fun pause() {
        routineJob?.pause()
        updateUiState { copy(state = StretchingRoutineState.PAUSED) }
    }

    fun onStopClick() {
        routineJob?.cancel()
        routineJob = null
        updateUiState {
            copy(
                state = StretchingRoutineState.IDLE,
                exercise = StringUiData.Empty,
                step = StringUiData.Empty,
            )
        }
    }

    fun onLanguageClick(supportedLocaleWithTextToSpeechAvailability: SupportedLocaleWithTextToSpeechAvailability) {
        localeManager.setLocale(supportedLocaleWithTextToSpeechAvailability.supportedLocale)
        fetchLocaleAvailability()
        fetchTextToSpeechEngines()
    }

    fun onEngineClick(engine: TextToSpeechEngine) {
        viewModelScope.launch {
            textToSpeechManager.setEngine(engine)
            fetchLocaleAvailability()
            updateUiState { copy(currentTextToSpeechEngine = engine) }
        }
    }

    fun onVoiceUnavailableDialogDismiss() {
        updateUiState { copy(showVoiceUnavailableDialog = null) }
    }

    fun onVoiceUnavailableDialogSettingsClick() {
        onVoiceUnavailableDialogDismiss()
        updateUiState { copy(showVoiceDownloadSettings = ShowVoiceDownloadSettings) }
    }

    fun onVoiceDownloadSettingsShown() {
        updateUiState { copy(showVoiceDownloadSettings = null) }
    }

    fun onVoiceDownloadSettingsResult() {
        fetchLocaleAvailability()
    }
}