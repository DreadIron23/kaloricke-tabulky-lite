package janmokry.kaloricketabulkylite.feature.foodexplorer.ui

import janmokry.kaloricketabulkylite.core.ui.MyApplicationTheme
import janmokry.kaloricketabulkylite.feature.foodexplorer.ui.FoodExplorerUiState.Success
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun FoodExplorerScreen(modifier: Modifier = Modifier, viewModel: FoodExplorerViewModel = hiltViewModel()) {
    val items by viewModel.uiState.collectAsStateWithLifecycle()
    if (items is Success) {
        FoodExplorerScreen(
            items = (items as Success).data,
            onSave = { name -> viewModel.addFoodExplorer(name) },
            modifier = modifier
        )
    }
}

@Composable
internal fun FoodExplorerScreen(
    items: List<String>,
    onSave: (name: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        var nameFoodExplorer by remember { mutableStateOf("Compose") }
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextField(
                value = nameFoodExplorer,
                onValueChange = { nameFoodExplorer = it }
            )

            Button(modifier = Modifier.width(96.dp), onClick = { onSave(nameFoodExplorer) }) {
                Text("Save")
            }
        }
        items.forEach {
            Text("Saved item: $it")
        }
    }
}

// Previews

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    MyApplicationTheme {
        FoodExplorerScreen(listOf("Compose", "Room", "Kotlin"), onSave = {})
    }
}

@Preview(showBackground = true, widthDp = 480)
@Composable
private fun PortraitPreview() {
    MyApplicationTheme {
        FoodExplorerScreen(listOf("Compose", "Room", "Kotlin"), onSave = {})
    }
}
