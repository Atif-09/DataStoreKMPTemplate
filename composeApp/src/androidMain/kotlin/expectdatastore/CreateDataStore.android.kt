package expectdatastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.runBlocking
import utils.ContextUtils

actual fun createDataStore(): DataStore<Preferences> {
    return runBlocking {
        getDataStore(producePath = { ContextUtils.dataStoreApplicationContext!!.filesDir.resolve(dataStoreFileName).absolutePath })
    }
}