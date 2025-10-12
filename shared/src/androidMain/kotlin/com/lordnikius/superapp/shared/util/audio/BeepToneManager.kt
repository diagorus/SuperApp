package com.lordnikius.superapp.shared.util.audio

import android.content.Context
import android.media.AudioManager
import android.media.ToneGenerator
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import kotlinx.coroutines.runBlocking
import superapp.shared.generated.resources.Res

actual class BeepToneManager(
    context: Context,
) {

    @SuppressWarnings("UnsafeOptInUsageError")
    private val mediaPlayer = ExoPlayer.Builder(context)
        .setMediaSourceFactory(
            DefaultMediaSourceFactory(
                ResolvingByteArrayDataSource.Factory { uri ->
                    runBlocking { Res.readBytes(uri.path!!) }
                }
            )
        )
        .build()

    private fun play(composeResourcesPath: String) {
        val toneGenerator = ToneGenerator(AudioManager.STREAM_MUSIC, 100)
        toneGenerator.startTone(ToneGenerator.TONE_CDMA_PIP, 150)
//        mediaPlayer.setMediaItem(MediaItem.fromUri(composeResourcesPath))
//        mediaPlayer.prepare()
//        mediaPlayer.play()
    }

    actual fun playSingleBeepTone() {
        play("files/audio/single_beep.wav")
    }

    actual fun playDoubleBeepTone() {
        play("files/audio/double_beep.wav")
        play("files/audio/double_beep.wav")
    }
}