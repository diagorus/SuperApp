package com.lordnikius.superapp.data.util.locale

import androidx.annotation.PluralsRes
import androidx.annotation.StringRes

sealed interface StringUiData {

    companion object {
        val Empty: StringUiData = Value("")
    }

    data class Value(val value: String) : StringUiData

    data class Resource(
        @StringRes val id: Int,
        val arguments: Array<Argument> = emptyArray(),
    ) : StringUiData

    data class Quantity(
        @PluralsRes val id: Int,
        val quantity: Int,
        val arguments: Array<Argument> = emptyArray(),
    ) : StringUiData

    sealed interface Argument {
        data class IntValue(val value: Int) : Argument

        data class StringValue(val value: String) : Argument

        data class StringResource(@StringRes val value: Int) : Argument
    }
}