package utils

import android.content.Context

object ContextUtils {

    var dataStoreApplicationContext: Context? = null

    fun setContext(context: Context) {
        dataStoreApplicationContext = context.applicationContext
    }
}
