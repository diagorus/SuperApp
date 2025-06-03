package com.lordnikius.superapp.util.locale

sealed interface StringUiData : android.os.Parcelable {

    companion object {
        val Empty: StringUiData = Value("")
    }

    @kotlinx.parcelize.Parcelize
    data class Value(val value: String) : StringUiData

    @kotlinx.parcelize.Parcelize
    data class Resource(
        @androidx.annotation.StringRes val id: Int,
        val arguments: Array<Argument> = emptyArray(),
    ) : StringUiData

    @kotlinx.parcelize.Parcelize
    data class Quantity(
        @androidx.annotation.PluralsRes val id: Int,
        val quantity: Int,
        val arguments: Array<Argument> = emptyArray(),
    ) : StringUiData

    @androidx.compose.runtime.Composable
    fun transformToString(): String {
        val context = androidx.compose.ui.platform.LocalContext.current
        return transformToString(context)
    }

    @SuppressWarnings("SpreadOperator")
    fun transformToString(context: android.content.Context): String {
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

    private fun Array<Argument>.transformArguments(context: android.content.Context): Array<Any> {
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

    sealed interface Argument : android.os.Parcelable {
        @kotlinx.parcelize.Parcelize
        data class IntValue(val value: Int) : Argument

        @kotlinx.parcelize.Parcelize
        data class StringValue(val value: String) : Argument

        @kotlinx.parcelize.Parcelize
        data class StringResource(@androidx.annotation.StringRes val value: Int) : Argument
    }
}