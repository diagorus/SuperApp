package com.lordnikius.superapp.shared.util.audio

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import kotlinx.coroutines.runBlocking
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Single
import superapp.shared.generated.resources.Res

@Single
class BeepToneManager(
    context: Context,
) {

    @SuppressWarnings("UnsafeOptInUsageError")
    private val mediaPlayer = ExoPlayer.Builder(context).setMediaSourceFactory(
        DefaultMediaSourceFactory(
            ResolvingByteArrayDataSource.Factory { uri ->
                runBlocking { Res.readBytes(uri.path!!) }
            }
        )
    ).build()

    private fun play(composeResourcesPath: String) {
        mediaPlayer.setMediaItem(MediaItem.fromUri(composeResourcesPath))
        mediaPlayer.prepare()
        mediaPlayer.play()
    }

    fun playSingleBeepTone() {
        play("files/audio/single_beep.wav")
    }

    fun playDoubleBeepTone() {
        play("files/audio/double_beep.wav")
    }
}