package com.lordnikius.nstretching.shared.util.audio

expect class BeepToneManager {
    suspend fun playBeep()
    suspend fun playDoubleBeep()
}