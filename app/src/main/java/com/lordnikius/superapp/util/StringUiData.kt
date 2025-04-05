@file:SuppressWarnings("NoComposablePreview", "TopLevelComposableFunctions")

package com.lordnikius.superapp.util

import android.content.Context
import android.os.Parcelable
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import kotlinx.parcelize.Parcelize

sealed interface StringUiData : Parcelable {

    companion object {
        val Empty: StringUiData = Value("")
    }

    @Parcelize
    data class Value(val value: String) : StringUiData

    @Parcelize
    data class Resource(
        @StringRes val id: Int,
        val arguments: Array<Argument> = emptyArray(),
    ) : StringUiData

    @Parcelize
    data class Quantity(
        @PluralsRes val id: Int,
        val quantity: Int,
        val arguments: Array<Argument> = emptyArray(),
    ) : StringUiData

    @Composable
    fun transformToString(): String {
        val context = LocalContext.current
        return transformToString(context)
    }

    @SuppressWarnings("SpreadOperator")
    fun transformToString(context: Context): String {
//        val localisedContext = ContextCompat.getContextForLanguage(context)
        return when (this) {
            is Value -> {
                value
            }
            is Resource -> {
                val transformedArgs = arguments.transformArguments(context)
                context.getString(id, *transformedArgs)
            }
            is Quantity -> {
                val transformedArgs = arguments.transformArguments(context)
                context.resources.getQuantityString(id, quantity, *transformedArgs)
            }
        }
    }

    private fun Array<Argument>.transformArguments(context: Context): Array<Any> {
        return map {
            when (it) {
                is Argument.IntValue -> {
                    it.value
                }
                is Argument.StringValue -> {
                    it.value
                }
                is Argument.StringResource -> {
                    context.getString(it.value)
                }
            }
        }
            .toTypedArray<Any>()
    }

    sealed interface Argument : Parcelable {
        @Parcelize
        data class IntValue(val value: Int) : Argument

        @Parcelize
        data class StringValue(val value: String) : Argument

        @Parcelize
        data class StringResource(@StringRes val value: Int) : Argument
    }
}
