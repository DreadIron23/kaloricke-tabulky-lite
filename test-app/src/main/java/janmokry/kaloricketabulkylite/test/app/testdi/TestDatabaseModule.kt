package janmokry.kaloricketabulkylite.test.app.testdi

import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import janmokry.kaloricketabulkylite.core.data.FoodExplorerRepository
import janmokry.kaloricketabulkylite.core.data.di.DataModule
import janmokry.kaloricketabulkylite.core.data.di.FakeFoodExplorerRepository

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DataModule::class]
)
interface FakeDataModule {

    @Binds
    abstract fun bindRepository(
        fakeRepository: FakeFoodExplorerRepository
    ): FoodExplorerRepository
}
