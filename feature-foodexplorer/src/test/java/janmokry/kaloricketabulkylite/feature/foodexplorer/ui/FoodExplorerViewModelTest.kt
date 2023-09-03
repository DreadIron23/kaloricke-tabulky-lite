package janmokry.kaloricketabulkylite.feature.foodexplorer.ui.foodexplorer


import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import janmokry.kaloricketabulkylite.feature.foodexplorer.repository.FoodExplorerRepository
import janmokry.kaloricketabulkylite.feature.foodexplorer.presentation.viewmodel.FoodExplorerUiState
import janmokry.kaloricketabulkylite.feature.foodexplorer.presentation.viewmodel.FoodExplorerViewModel

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@OptIn(ExperimentalCoroutinesApi::class)
class FoodExplorerViewModelTest {
    @Test
    fun uiState_initiallyLoading() = runTest {
        val viewModel = FoodExplorerViewModel(FakeFoodExplorerRepository())
        assertEquals(viewModel.uiState.first(), FoodExplorerUiState.Loading)
    }

    @Test
    fun uiState_onItemSaved_isDisplayed() = runTest {
        val viewModel = FoodExplorerViewModel(FakeFoodExplorerRepository())
        assertEquals(viewModel.uiState.first(), FoodExplorerUiState.Loading)
    }
}

private class FakeFoodExplorerRepository : FoodExplorerRepository {

    private val data = mutableListOf<String>()

    override val getFoodList: Flow<List<String>>
        get() = flow { emit(data.toList()) }

    override suspend fun add(name: String) {
        data.add(0, name)
    }
}
