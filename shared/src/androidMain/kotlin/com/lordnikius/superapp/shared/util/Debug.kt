package com.lordnikius.superapp.shared.util

import com.nikitabilous.androidlibrary.BuildConfig

actual fun isDebug(): Boolean {
    return BuildConfig.DEBUG
}