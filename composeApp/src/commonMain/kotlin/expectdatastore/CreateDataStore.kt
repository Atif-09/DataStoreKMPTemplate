package expectdatastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

expect fun createDataStore(): DataStore<Preferences>

internal const val dataStoreFileName = "create_datastore.preferences_pb"