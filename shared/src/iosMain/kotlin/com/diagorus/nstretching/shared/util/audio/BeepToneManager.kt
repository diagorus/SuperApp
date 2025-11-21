package com.diagorus.nstretching.shared.util.audio

import platform.AudioToolbox.AudioServicesPlaySystemSound

actual class BeepToneManager {

    actual suspend fun playBeep() {
        AudioServicesPlaySystemSound(1103.toUInt())
    }

    actual suspend fun playDoubleBeep() {
        playBeep()
        playBeep()
    }
}