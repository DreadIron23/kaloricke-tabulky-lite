package janmokry.kaloricketabulkylite.feature.foodexplorer.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import janmokry.kaloricketabulkylite.feature.foodexplorer.domain.usecase.GetFoodDetailUseCase
import janmokry.kaloricketabulkylite.feature.foodexplorer.presentation.mapper.FoodDetailViewDataMapper
import janmokry.kaloricketabulkylite.feature.foodexplorer.presentation.view.viewdata.FoodDetailViewData
import janmokry.kaloricketabulkylite.feature.foodexplorer.presentation.view.viewdata.multiplyBy
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FoodDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getFoodDetailUseCase: GetFoodDetailUseCase,
    foodDetailViewDataMapper: FoodDetailViewDataMapper,
): ViewModel() {

    private val fetchedDataResult = flow {
        val foodGuidArg: String = savedStateHandle["foodDetailGuid"]!!
        emit(
            getFoodDetailUseCase(foodGuidArg)
                .map(foodDetailViewDataMapper::mapToViewData)
        )
    }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            null,
        )

    /**
     * E.g. 100g or 100ml. Updated from UI.
     */
    private val currentQuantity = MutableStateFlow(INITIAL_QUANTITY)

    /**
     * E.g. 5x of [currentQuantity]. Updated from UI.
     */
    private val currentMultiplier = MutableStateFlow(INITIAL_MULTIPLIER)

    val uiState =
        combine(
            fetchedDataResult.filterNotNull(),
            currentQuantity,
            currentMultiplier,
        ) { fetchedResult, quantity, multiplier ->
            fetchedResult.fold(
                onSuccess = { viewData ->
                    FoodDetailUiState.Success(
                        viewData.multiplyBy(quantity * multiplier)
                    )
                },
                onFailure = FoodDetailUiState::Error,
            )
        }

    fun onQuantityChange(change: Double) {
        currentQuantity.value = change
    }

    fun onMultiplierChange(change: Double) {
        currentMultiplier.value = change
    }
}

sealed interface FoodDetailUiState {

    data object Loading : FoodDetailUiState

    data class Error(val throwable: Throwable? = null) : FoodDetailUiState

    data class Success(val viewData: FoodDetailViewData) : FoodDetailUiState
}

private const val INITIAL_QUANTITY = 100.0
private const val INITIAL_MULTIPLIER = 1.0
