package com.lordnikius.superapp.shared.stretching.data.routine.exercise

import com.lordnikius.superapp.shared.stretching.data.routine.exercise.step.ChangeStep
import com.lordnikius.superapp.shared.stretching.data.routine.exercise.step.PreparationStep
import com.lordnikius.superapp.shared.stretching.data.routine.exercise.step.StretchStep
import com.lordnikius.superapp.shared.util.config.StretchingConfig
import kotlinx.coroutines.flow.flowOf
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Provided
import superapp.shared.generated.resources.Res
import superapp.shared.generated.resources.quads

@Factory
class QuadsExercise(
    @Provided config: StretchingConfig,
) : StretchingExercise(
    nameRes = Res.string.quads,
    steps = flowOf(
        PreparationStep(Res.string.quads, config.quadsPreparationStepDuration),
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