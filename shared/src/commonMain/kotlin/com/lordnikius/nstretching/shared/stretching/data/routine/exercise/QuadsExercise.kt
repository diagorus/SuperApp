package com.lordnikius.nstretching.shared.stretching.data.routine.exercise

import com.lordnikius.nstretching.shared.stretching.data.routine.exercise.step.ChangeStep
import com.lordnikius.nstretching.shared.stretching.data.routine.exercise.step.PreparationStep
import com.lordnikius.nstretching.shared.stretching.data.routine.exercise.step.StretchStep
import com.lordnikius.nstretching.shared.util.config.StretchingConfig
import kotlinx.coroutines.flow.flowOf
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Provided
import com.lordnikius.nstretching.shared.generated.resources.Res
import com.lordnikius.nstretching.shared.generated.resources.quads

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