package janmokry.kaloricketabulkylite.feature.foodexplorer.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import janmokry.kaloricketabulkylite.core.data.FoodExplorerRepository
import janmokry.kaloricketabulkylite.feature.foodexplorer.ui.FoodExplorerUiState.Error
import janmokry.kaloricketabulkylite.feature.foodexplorer.ui.FoodExplorerUiState.Loading
import janmokry.kaloricketabulkylite.feature.foodexplorer.ui.FoodExplorerUiState.Success
import javax.inject.Inject

@HiltViewModel
class FoodExplorerViewModel @Inject constructor(
    private val foodExplorerRepository: FoodExplorerRepository
) : ViewModel() {

    val uiState: StateFlow<FoodExplorerUiState> = foodExplorerRepository
        .foodExplorers.map<List<String>, FoodExplorerUiState> { Success(data = it) }
        .catch { emit(Error(it)) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Loading)

    fun addFoodExplorer(name: String) {
        viewModelScope.launch {
            foodExplorerRepository.add(name)
        }
    }
}

sealed interface FoodExplorerUiState {
    object Loading : FoodExplorerUiState
    data class Error(val throwable: Throwable) : FoodExplorerUiState
    data class Success(val data: List<String>) : FoodExplorerUiState
}
