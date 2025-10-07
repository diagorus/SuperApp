package com.lordnikius.superapp.shared.stretching.routine.exercise

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.koin.core.annotation.Single

@Single
class StretchingRoutineRepository(
    private val handsExercise: HandsExercise,
    private val backTwistsExercise: BackTwistsExercise,
    private val longitudinalTwineExercise: LongitudinalTwineExercise,
    private val transverseTwineExercise: TransverseTwineExercise,
    private val quadsExercise: QuadsExercise,
    private val buttAndBackExercise: ButtAndBackExercise,
    private val routineEndExercise: RoutineEndExercise,
) {

    fun getRoutine(): Flow<StretchingExercise> {
        return flowOf(
            handsExercise,
            backTwistsExercise,
            longitudinalTwineExercise,
            transverseTwineExercise,
            quadsExercise,
            buttAndBackExercise,
            routineEndExercise,
        )
    }
}