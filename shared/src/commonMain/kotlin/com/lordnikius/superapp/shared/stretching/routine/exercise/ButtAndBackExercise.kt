package com.lordnikius.superapp.shared.stretching.routine.exercise

import com.lordnikius.superapp.shared.stretching.routine.exercise.step.ChangeStep
import com.lordnikius.superapp.shared.stretching.routine.exercise.step.PreparationStep
import com.lordnikius.superapp.shared.stretching.routine.exercise.step.RelaxStep
import com.lordnikius.superapp.shared.stretching.routine.exercise.step.StretchStep
import com.lordnikius.superapp.shared.util.config.StretchingConfig
import kotlinx.coroutines.flow.flowOf
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Provided
import superapp.shared.generated.resources.Res
import superapp.shared.generated.resources.back
import superapp.shared.generated.resources.butt_and_back

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