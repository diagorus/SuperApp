package com.diagorus.nstretching.shared.stretching.data.routine.exercise

import com.diagorus.nstretching.shared.stretching.data.routine.exercise.step.ChangeStep
import com.diagorus.nstretching.shared.stretching.data.routine.exercise.step.PreparationStep
import com.diagorus.nstretching.shared.stretching.data.routine.exercise.step.StretchStep
import com.diagorus.nstretching.shared.util.config.StretchingConfig
import kotlinx.coroutines.flow.flowOf
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Provided
import com.diagorus.nstretching.shared.generated.resources.Res
import com.diagorus.nstretching.shared.generated.resources.quads

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