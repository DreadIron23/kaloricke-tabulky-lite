package janmokry.kaloricketabulkylite.feature.foodexplorer.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import janmokry.kaloricketabulkylite.feature.foodexplorer.domain.usecase.GetFoodListUseCase
import janmokry.kaloricketabulkylite.feature.foodexplorer.presentation.mapper.FoodListViewDataMapper
import janmokry.kaloricketabulkylite.feature.foodexplorer.presentation.view.viewdata.FoodItemViewData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FoodExplorerViewModel @Inject constructor(
    private val getFoodListUseCase: GetFoodListUseCase,
    private val foodListViewDataMapper: FoodListViewDataMapper,
) : ViewModel() {

    private val searchQuery = MutableStateFlow("")

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val uiState = searchQuery
        .debounce(300L)
        .mapLatest(getFoodListUseCase::invoke)
        .map { result ->
            result.fold(
                onSuccess = foodListViewDataMapper::mapToViewData,
                onFailure = FoodExplorerUiState::Error
            )
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            FoodExplorerUiState.Loading
        )

    fun onSearchQueryChange(query: String) {
        searchQuery.value = query.takeUnless { it.length in 1..2 } ?: ""
    }
}

sealed interface FoodExplorerUiState {

    data object Loading : FoodExplorerUiState

    data object NotFound : FoodExplorerUiState

    data class Error(val throwable: Throwable? = null) : FoodExplorerUiState

    data class Success(val foodList: List<FoodItemViewData>) : FoodExplorerUiState
}
