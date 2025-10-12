package com.lordnikius.superapp.shared.util.textToSpeech.auxiliaries

import kotlin.coroutines.Continuation

class Utterance(val id: String, val text: String, val queueMode: Int, val continuation: Continuation<Unit>)