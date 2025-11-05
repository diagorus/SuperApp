package com.lordnikius.nstretching.shared.stretching.data.routine.exercise

import com.lordnikius.nstretching.shared.stretching.data.routine.exercise.step.ChangeStep
import com.lordnikius.nstretching.shared.stretching.data.routine.exercise.step.PreparationStep
import com.lordnikius.nstretching.shared.stretching.data.routine.exercise.step.RelaxStep
import com.lordnikius.nstretching.shared.stretching.data.routine.exercise.step.StretchStep
import com.lordnikius.nstretching.shared.util.config.StretchingConfig
import kotlinx.coroutines.flow.flowOf
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Provided
import com.lordnikius.nstretching.shared.generated.resources.Res
import com.lordnikius.nstretching.shared.generated.resources.back
import com.lordnikius.nstretching.shared.generated.resources.butt_and_back

@Factory
class ButtAndBackExercise(
    @Provided config: StretchingConfig,
) : StretchingExercise(
    nameRes = Res.string.butt_and_back,
    steps = flowOf(
        PreparationStep(Res.string.butt_and_back, config.buttAndBackPreparationStepDuration),
        StretchStep(config),
        ChangeStep(config),
        StretchStep(config),
        PreparationStep(Res.string.back, config.buttAndBackPreparationStepDuration),
        StretchStep(config),
        RelaxStep(config),
        StretchStep(config),
        ChangeStep(config),
        StretchStep(config),
        PreparationStep(Res.string.back, config.buttAndBackPreparationStepDuration),
        StretchStep(config),
        RelaxStep(config),
        StretchStep(config),
        ChangeStep(config),
        StretchStep(config),
        PreparationStep(Res.string.back, config.buttAndBackPreparationStepDuration),
        StretchStep(config),
    ),
)