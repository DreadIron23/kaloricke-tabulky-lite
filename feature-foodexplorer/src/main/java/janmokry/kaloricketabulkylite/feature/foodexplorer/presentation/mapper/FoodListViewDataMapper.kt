package janmokry.kaloricketabulkylite.feature.foodexplorer.presentation.mapper

import janmokry.kaloricketabulkylite.feature.foodexplorer.domain.data.FoodListData
import janmokry.kaloricketabulkylite.feature.foodexplorer.presentation.view.viewdata.FoodItemViewData
import janmokry.kaloricketabulkylite.feature.foodexplorer.presentation.viewmodel.FoodExplorerUiState
import javax.inject.Inject

interface FoodListViewDataMapper {

    fun mapToViewData(data: FoodListData): FoodExplorerUiState
}

class FoodListViewDataMapperImpl @Inject constructor() : FoodListViewDataMapper {

    override fun mapToViewData(data: FoodListData) = data.map()

    private fun FoodListData.map(): FoodExplorerUiState = when(this) {
        FoodListData.NotFound -> FoodExplorerUiState.NotFound
        FoodListData.Error -> FoodExplorerUiState.Error()
        is FoodListData.Data ->
            FoodExplorerUiState.Success(
                foodList.map {
                    FoodItemViewData(
                        guid = it.guidFood,
                        name = it.name,
                        energyUnit = it.energyUnit,
                        energyValue = "${
                            it.energyValue.toDouble().toInt()
                        } kcal",
                        imageThumbnailUrl = it.photoThumb
                    )
                }
        )
    }
}