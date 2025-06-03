package com.lordnikius.superapp.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesDataStoreManager @Inject constructor(
    @ApplicationContext private val appContext: Context,
) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)

    private val KEY_CHOSEN_TEXT_TO_SPEECH_ENGINE_PACKAGE =
        stringPreferencesKey(KEY_NAME_CHOSEN_TEXT_TO_SPEECH_ENGINE_PACKAGE)

    val chosenTextToSpeechEnginePackageFlow: Flow<String?> = appContext.dataStore.data.map {
        it[KEY_CHOSEN_TEXT_TO_SPEECH_ENGINE_PACKAGE]
    }

    suspend fun saveChosenTextToSpeechEnginePackage(newPackage: String) {
        appContext.dataStore.edit {
            it[KEY_CHOSEN_TEXT_TO_SPEECH_ENGINE_PACKAGE] = newPackage
        }
    }

    companion object {
        private const val PREFERENCES_NAME = "preferences"
        private const val KEY_NAME_CHOSEN_TEXT_TO_SPEECH_ENGINE_PACKAGE = "chosen_text_to_speech_engine_package"
    }
}