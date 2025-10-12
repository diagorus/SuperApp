package com.lordnikius.superapp.shared.stretching.data.routine.exercise

import com.lordnikius.superapp.shared.stretching.data.routine.exercise.step.PreparationStep
import com.lordnikius.superapp.shared.stretching.data.routine.exercise.step.RelaxStep
import com.lordnikius.superapp.shared.stretching.data.routine.exercise.step.StretchStep
import com.lordnikius.superapp.shared.util.config.StretchingConfig
import kotlinx.coroutines.flow.flowOf
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Provided
import superapp.shared.generated.resources.Res
import superapp.shared.generated.resources.hands

@Factory
class HandsExercise(
    @Provided config: StretchingConfig,
) : StretchingExercise(
    nameRes = Res.string.hands,
    steps = flowOf(
        PreparationStep(Res.string.hands, config.handsPreparationStepDuration),
        StretchStep(config),
        RelaxStep(config),
        StretchStep(config),
        RelaxStep(config),
        StretchStep(config),
    )
)