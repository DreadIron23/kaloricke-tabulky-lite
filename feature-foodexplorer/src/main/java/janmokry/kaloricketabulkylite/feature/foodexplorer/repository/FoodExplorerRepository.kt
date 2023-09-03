package janmokry.kaloricketabulkylite.feature.foodexplorer.repository

import janmokry.kaloricketabulkylite.feature.foodexplorer.domain.data.FoodListData
import janmokry.kaloricketabulkylite.feature.foodexplorer.repository.api.FoodApi
import janmokry.kaloricketabulkylite.feature.foodexplorer.repository.mapper.FoodListDataMapper
import javax.inject.Inject

interface FoodExplorerRepository {

    suspend fun getFoodList(query: String?): Result<FoodListData>
}

class FoodExplorerRepositoryImpl @Inject constructor(
    private val foodApi: FoodApi,
    private val foodListDataMapper: FoodListDataMapper,
) : FoodExplorerRepository {

    override suspend fun getFoodList(query: String?): Result<FoodListData> {
        val actualQuery = if (query.isNullOrBlank()) null else query
        return foodApi.getFoodList(actualQuery)
            .map(foodListDataMapper::mapToData)
    }
}
