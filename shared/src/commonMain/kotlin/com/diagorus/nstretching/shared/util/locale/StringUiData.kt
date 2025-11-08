package com.diagorus.nstretching.shared.util.locale

import org.jetbrains.compose.resources.PluralStringResource
import org.jetbrains.compose.resources.StringResource

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