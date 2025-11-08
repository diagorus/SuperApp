package com.diagorus.nstretching.shared.util.audio

import android.media.AudioManager
import android.media.ToneGenerator
import com.diagorus.nstretching.shared.util.koin.CoroutinesModule.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

actual class BeepToneManager(
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
) {

    private val toneGenerator = ToneGenerator(AudioManager.STREAM_MUSIC, TONE_VOLUME)

    actual suspend fun playBeep() {
        play(ToneGenerator.TONE_PROP_BEEP)
    }

    private suspend fun play(toneType: Int) {
        withContext(defaultDispatcher) {
            toneGenerator.startTone(toneType)
        }
    }

    actual suspend fun playDoubleBeep() {
        play(ToneGenerator.TONE_PROP_BEEP2)
    }

    companion object {
        private const val TONE_VOLUME = 100
    }
}