package com.lordnikius.superapp.routine

import com.lordnikius.superapp.routine.exercise.BackTwistsExercise
import com.lordnikius.superapp.routine.exercise.ButtAndBackExercise
import com.lordnikius.superapp.routine.exercise.LongitudinalTwineExercise
import com.lordnikius.superapp.routine.exercise.QuadsExercise
import com.lordnikius.superapp.routine.exercise.StretchingExercise
import com.lordnikius.superapp.routine.exercise.TransverseTwineExercise
import com.lordnikius.superapp.routine.exercise.HandsExercise
import com.lordnikius.superapp.routine.exercise.RoutineEndExercise
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class Routine @Inject constructor(
    handsExercise: HandsExercise,
    backTwistsExercise: BackTwistsExercise,
    longitudinalTwineExercise: LongitudinalTwineExercise,
    transverseTwineExercise: TransverseTwineExercise,
    quadsExercise: QuadsExercise,
    buttAndBackExercise: ButtAndBackExercise,
    routineEndExercise: RoutineEndExercise
) {

    private val exercises = flowOf(
        handsExercise,
        backTwistsExercise,
        longitudinalTwineExercise,
        transverseTwineExercise,
        quadsExercise,
        buttAndBackExercise,
        routineEndExercise,
    )

    val currentExercise: SharedFlow<StretchingExercise> = exercises.shareIn(GlobalScope, SharingStarted.Lazily)

    suspend fun start() {
        exercises.collect {
            it.start()
        }
    }
}