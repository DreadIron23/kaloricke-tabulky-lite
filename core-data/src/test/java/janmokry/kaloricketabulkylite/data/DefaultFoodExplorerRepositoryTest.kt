package janmokry.kaloricketabulkylite.data

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import janmokry.kaloricketabulkylite.core.data.DefaultFoodExplorerRepository
import janmokry.kaloricketabulkylite.core.database.FoodExplorer
import janmokry.kaloricketabulkylite.core.database.FoodExplorerDao

/**
 * Unit tests for [DefaultFoodExplorerRepository].
 */
@OptIn(ExperimentalCoroutinesApi::class) // TODO: Remove when stable
class DefaultFoodExplorerRepositoryTest {

    @Test
    fun foodExplorers_newItemSaved_itemIsReturned() = runTest {
        val repository = DefaultFoodExplorerRepository(FakeFoodExplorerDao())

        repository.add("Repository")

        assertEquals(repository.foodExplorers.first().size, 1)
    }

}

private class FakeFoodExplorerDao : FoodExplorerDao {

    private val data = mutableListOf<FoodExplorer>()

    override fun getFoodExplorers(): Flow<List<FoodExplorer>> = flow {
        emit(data)
    }

    override suspend fun insertFoodExplorer(item: FoodExplorer) {
        data.add(0, item)
    }
}
