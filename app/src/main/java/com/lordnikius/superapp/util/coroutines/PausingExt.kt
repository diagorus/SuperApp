package com.lordnikius.superapp.util.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import org.koitharu.pausingcoroutinedispatcher.PausingJob
import org.koitharu.pausingcoroutinedispatcher.launchPausing

fun <T> Flow<T>.launchInPausing(scope: CoroutineScope): PausingJob = scope.launchPausing {
    collect()
}