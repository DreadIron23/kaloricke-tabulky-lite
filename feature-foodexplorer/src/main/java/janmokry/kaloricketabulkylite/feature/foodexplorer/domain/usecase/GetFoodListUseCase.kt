package janmokry.kaloricketabulkylite.feature.foodexplorer.domain.usecase

import janmokry.kaloricketabulkylite.feature.foodexplorer.domain.data.FoodListData
import janmokry.kaloricketabulkylite.feature.foodexplorer.repository.FoodExplorerRepository
import javax.inject.Inject

interface GetFoodListUseCase {

    suspend operator fun invoke(query: String): Result<FoodListData>
}

class GetFoodListUseCaseImpl @Inject constructor(
    private val foodExplorerRepository: FoodExplorerRepository,
) : GetFoodListUseCase {

    override suspend fun invoke(query: String): Result<FoodListData> =
        foodExplorerRepository.getFoodList(query)
}
