package com.diagorus.nstretching.shared.util.audio

expect class BeepToneManager {
    suspend fun playBeep()
    suspend fun playDoubleBeep()
}