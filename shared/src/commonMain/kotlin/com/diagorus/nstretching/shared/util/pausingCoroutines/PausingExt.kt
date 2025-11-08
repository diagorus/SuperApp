package com.diagorus.nstretching.shared.util.pausingCoroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

fun <T> Flow<T>.launchInPausing(scope: CoroutineScope): PausingJob = scope.launchPausing {
    collect()
}