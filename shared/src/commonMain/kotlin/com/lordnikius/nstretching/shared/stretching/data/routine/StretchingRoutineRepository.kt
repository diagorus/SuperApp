package com.lordnikius.nstretching.shared.stretching.data.routine

import com.lordnikius.nstretching.shared.stretching.data.routine.exercise.BackTwistsExercise
import com.lordnikius.nstretching.shared.stretching.data.routine.exercise.ButtAndBackExercise
import com.lordnikius.nstretching.shared.stretching.data.routine.exercise.HandsExercise
import com.lordnikius.nstretching.shared.stretching.data.routine.exercise.LongitudinalTwineExercise
import com.lordnikius.nstretching.shared.stretching.data.routine.exercise.QuadsExercise
import com.lordnikius.nstretching.shared.stretching.data.routine.exercise.RoutineEndExercise
import com.lordnikius.nstretching.shared.stretching.data.routine.exercise.StretchingExercise
import com.lordnikius.nstretching.shared.stretching.data.routine.exercise.TransverseTwineExercise
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