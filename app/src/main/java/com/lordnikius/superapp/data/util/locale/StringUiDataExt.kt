package com.lordnikius.superapp.data.util.locale

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.lordnikius.superapp.data.util.locale.StringUiData.Argument
import com.lordnikius.superapp.data.util.locale.StringUiData.Quantity
import com.lordnikius.superapp.data.util.locale.StringUiData.Resource
import com.lordnikius.superapp.data.util.locale.StringUiData.Value

@Composable
fun StringUiData.transformToString(): String {
    val context = LocalContext.current
    return transformToString(context)
}

@SuppressWarnings("SpreadOperator")
fun StringUiData.transformToString(context: Context): String {
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