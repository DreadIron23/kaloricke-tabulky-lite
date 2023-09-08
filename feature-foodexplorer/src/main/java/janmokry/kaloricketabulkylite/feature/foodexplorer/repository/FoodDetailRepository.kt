package janmokry.kaloricketabulkylite.feature.foodexplorer.repository

import janmokry.kaloricketabulkylite.feature.foodexplorer.domain.data.FoodDetailData
import janmokry.kaloricketabulkylite.feature.foodexplorer.repository.api.FoodDetailApi
import janmokry.kaloricketabulkylite.feature.foodexplorer.repository.mapper.FoodDetailDataMapper
import javax.inject.Inject

interface FoodDetailRepository {

    suspend fun getFoodDetail(foodGuid: String): Result<FoodDetailData>
}

class FoodDetailRepositoryImpl @Inject constructor(
    private val foodDetailApi: FoodDetailApi,
    private val foodDetailDataMapper: FoodDetailDataMapper,
) : FoodDetailRepository {

    override suspend fun getFoodDetail(foodGuid: String): Result<FoodDetailData> =
        foodDetailApi.getFoodDetail(foodGuid)
            .map(foodDetailDataMapper::mapToData)
}
