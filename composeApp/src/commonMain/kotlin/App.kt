import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import org.jetbrains.compose.ui.tooling.preview.Preview

import expectdatastore.createDataStore
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

val dataStore = createDataStore()
@Composable
@Preview
fun App() {
    MaterialTheme {
        val scope = rememberCoroutineScope()
        val  dataStoreExample = stringPreferencesKey("data_store")
        var userInput by remember { mutableStateOf("") }
        var isError by remember { mutableStateOf(false) }
        var dataStoreValue by remember { mutableStateOf("") }

        LaunchedEffect(Unit){
          dataStore.data.map { preferences ->
                preferences[dataStoreExample] ?: ""
            }.collectLatest {
              dataStoreValue = it
          }
        }
        println("Data Store value is $dataStoreValue")

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
                    Text("DataStore value is: $dataStoreValue", modifier = Modifier.padding(vertical = 18.dp))


                OutlinedTextField(
                    value = userInput,
                    onValueChange = {userInput = it},
                    placeholder = { Text("Please enter some value") },
                    label = { Text("DataStore Input") },
                    isError = isError
                )
                if (isError){
                    Text("Input value should not be empty")
                }

                Button(onClick = {
                    if (userInput.isEmpty()){
                        isError = true
                    } else {
                        isError = false
                        scope.launch {

                            dataStore.edit {
                                it[dataStoreExample] = userInput

                            }
                        }
                    }
                }){
                    Text("Save to DataStore")
                }
            }
        }


    }
}



