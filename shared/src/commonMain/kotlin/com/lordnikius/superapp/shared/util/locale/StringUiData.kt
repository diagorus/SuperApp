package com.lordnikius.superapp.shared.util.locale

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.PluralStringResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.getPluralString
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.pluralStringResource
import org.jetbrains.compose.resources.stringResource

sealed interface StringUiData {

    companion object {
        val Empty: StringUiData = Value("")
    }

    data class Value(val value: String) : StringUiData

    data class Resource(
        val resource: StringResource,
        val arguments: List<Argument> = emptyList(),
    ) : StringUiData

    data class Quantity(
        val resource: PluralStringResource,
        val quantity: Int,
        val arguments: List<Argument> = emptyList(),
    ) : StringUiData

    sealed interface Argument {

        data class IntValue(val value: Int) : Argument

        data class StringValue(val value: String) : Argument

        data class StringRes(val resource: StringResource) : Argument
    }
}