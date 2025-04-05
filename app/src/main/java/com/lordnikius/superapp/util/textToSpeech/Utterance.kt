package com.lordnikius.superapp.util.textToSpeech

import kotlin.coroutines.Continuation

class Utterance(val id: String, val text: String, val queueMode: Int, val continuation: Continuation<Unit>)