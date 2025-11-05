package com.lordnikius.nstretching.shared.stretching.data.routine.exercise

import com.lordnikius.nstretching.shared.stretching.data.routine.exercise.step.ChangeStep
import com.lordnikius.nstretching.shared.stretching.data.routine.exercise.step.PreparationStep
import com.lordnikius.nstretching.shared.stretching.data.routine.exercise.step.StretchStep
import com.lordnikius.nstretching.shared.util.config.StretchingConfig
import kotlinx.coroutines.flow.flowOf
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Provided
import com.lordnikius.nstretching.shared.generated.resources.Res
import com.lordnikius.nstretching.shared.generated.resources.longitudinal_twine

@Factory
class LongitudinalTwineExercise(
    @Provided config: StretchingConfig,
) : StretchingExercise(
    nameRes = Res.string.longitudinal_twine,
    steps = flowOf(
        PreparationStep(Res.string.longitudinal_twine, config.longitudinalTwinePreparationStepDuration),
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