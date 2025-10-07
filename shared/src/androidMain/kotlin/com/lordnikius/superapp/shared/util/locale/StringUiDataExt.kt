package com.lordnikius.superapp.shared.util.locale

import androidx.compose.runtime.Composable
import com.lordnikius.superapp.shared.util.locale.StringUiData.Argument
import com.lordnikius.superapp.shared.util.locale.StringUiData.Quantity
import com.lordnikius.superapp.shared.util.locale.StringUiData.Resource
import com.lordnikius.superapp.shared.util.locale.StringUiData.Value
import org.jetbrains.compose.resources.getPluralString
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.pluralStringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun StringUiData.stringResource(): String {
    return when (this) {
        is Value -> {
            value
        }
        is Resource -> {
            val transformedArgs = arguments.transformArgumentsCompose()
            stringResource(resource, *transformedArgs)
        }
        is Quantity -> {
            val transformedArgs = arguments.transformArgumentsCompose()
            pluralStringResource(resource, quantity, *transformedArgs)
        }
    }
}

@Composable
private fun List<Argument>.transformArgumentsCompose(): Array<Any> {
    return map {
        when (it) {
            is Argument.IntValue -> {
                it.value
            }
            is Argument.StringValue -> {
                it.value
            }
            is Argument.StringRes -> {
                stringResource(it.resource)
            }
        }
    }
        .toTypedArray<Any>()
}

suspend fun StringUiData.transformToString(): String {
    return when (this) {
        is Value -> {
            value
        }
        is Resource -> {
            val transformedArgs = arguments.transformArguments()
            getString(resource, *transformedArgs)
        }
        is Quantity -> {
            val transformedArgs = arguments.transformArguments()
            getPluralString(resource, quantity, *transformedArgs)
        }
    }
}

private suspend fun List<Argument>.transformArguments(): Array<Any> {
    return map {
        when (it) {
            is Argument.IntValue -> {
                it.value
            }
            is Argument.StringValue -> {
                it.value
            }
            is Argument.StringRes -> {
                getString(it.resource)
            }
        }
    }
        .toTypedArray<Any>()
}