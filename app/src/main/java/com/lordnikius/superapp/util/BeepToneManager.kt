package com.lordnikius.superapp.util

import android.content.Context
import android.media.MediaPlayer
import androidx.annotation.RawRes
import com.lordnikius.superapp.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BeepToneManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun playSingleBeepTone() {
        val singleBeepTonePlayer = setupPlayerFor(R.raw.single_beep)
        singleBeepTonePlayer.start()
    }

    private fun setupPlayerFor(@RawRes id: Int): MediaPlayer {
        return MediaPlayer.create(context, id)
//            .apply {
//                setOnCompletionListener {
//                    it.release()
//                }
//            }
    }

    fun playDoubleBeepTone() {
        val doubleBeepTonePlayer = setupPlayerFor(R.raw.double_beep)
        doubleBeepTonePlayer.start()
    }
}