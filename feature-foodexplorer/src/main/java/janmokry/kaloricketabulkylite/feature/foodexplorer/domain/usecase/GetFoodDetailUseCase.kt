package janmokry.kaloricketabulkylite.feature.foodexplorer.domain.usecase

import janmokry.kaloricketabulkylite.feature.foodexplorer.domain.data.FoodDetailData
import janmokry.kaloricketabulkylite.feature.foodexplorer.repository.FoodDetailRepository
import javax.inject.Inject

interface GetFoodDetailUseCase {

    suspend operator fun invoke(foodGuid: String): Result<FoodDetailData>
}

class GetFoodDetailUseCaseImpl @Inject constructor(
    private val foodDetailRepository: FoodDetailRepository,
) : GetFoodDetailUseCase {

    override suspend fun invoke(foodGuid: String): Result<FoodDetailData> =
        foodDetailRepository.getFoodDetail(foodGuid)
}
