package janmokry.kaloricketabulkylite.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import janmokry.kaloricketabulkylite.core.data.FoodExplorerRepository
import janmokry.kaloricketabulkylite.core.data.DefaultFoodExplorerRepository
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Singleton
    @Binds
    fun bindsFoodExplorerRepository(
        foodExplorerRepository: DefaultFoodExplorerRepository
    ): FoodExplorerRepository
}

class FakeFoodExplorerRepository @Inject constructor() : FoodExplorerRepository {
    override val foodExplorers: Flow<List<String>> = flowOf(fakeFoodExplorers)

    override suspend fun add(name: String) {
        throw NotImplementedError()
    }
}

val fakeFoodExplorers = listOf("One", "Two", "Three")
