package janmokry.kaloricketabulkylite.core.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import janmokry.kaloricketabulkylite.core.database.FoodExplorer
import janmokry.kaloricketabulkylite.core.database.FoodExplorerDao
import javax.inject.Inject

interface FoodExplorerRepository {
    val foodExplorers: Flow<List<String>>

    suspend fun add(name: String)
}

class DefaultFoodExplorerRepository @Inject constructor(
    private val foodExplorerDao: FoodExplorerDao
) : FoodExplorerRepository {

    override val foodExplorers: Flow<List<String>> =
        foodExplorerDao.getFoodExplorers().map { items -> items.map { it.name } }

    override suspend fun add(name: String) {
        foodExplorerDao.insertFoodExplorer(FoodExplorer(name = name))
    }
}
