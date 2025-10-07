package com.lordnikius.superapp.shared.stretching.routine.exercise

import com.lordnikius.superapp.shared.stretching.routine.exercise.step.StretchStep
import com.lordnikius.superapp.shared.stretching.routine.exercise.step.ChangeStep
import com.lordnikius.superapp.shared.stretching.routine.exercise.step.PreparationStep
import com.lordnikius.superapp.shared.util.config.StretchingConfig
import kotlinx.coroutines.flow.flowOf
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Provided
import superapp.shared.generated.resources.Res
import superapp.shared.generated.resources.back_twists

@Factory
class BackTwistsExercise(
    @Provided config: StretchingConfig,
) : StretchingExercise(
    nameRes = Res.string.back_twists,
    steps = flowOf(
        PreparationStep(
            Res.string.back_twists,
            config.backTwistsPreparationStepDuration,
        ),
        StretchStep(config),
        ChangeStep(config),
        StretchStep(config),
        ChangeStep(config),
        StretchStep(config),
        ChangeStep(config),
        StretchStep(config),
        ChangeStep(config),
        StretchStep(config),
        ChangeStep(config),
        StretchStep(config),
    ),
)