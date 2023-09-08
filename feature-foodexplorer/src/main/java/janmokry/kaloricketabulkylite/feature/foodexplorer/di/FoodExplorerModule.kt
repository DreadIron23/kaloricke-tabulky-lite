package janmokry.kaloricketabulkylite.feature.foodexplorer.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import janmokry.kaloricketabulkylite.feature.foodexplorer.domain.usecase.GetFoodDetailUseCase
import janmokry.kaloricketabulkylite.feature.foodexplorer.domain.usecase.GetFoodDetailUseCaseImpl
import janmokry.kaloricketabulkylite.feature.foodexplorer.domain.usecase.GetFoodListUseCase
import janmokry.kaloricketabulkylite.feature.foodexplorer.domain.usecase.GetFoodListUseCaseImpl
import janmokry.kaloricketabulkylite.feature.foodexplorer.presentation.mapper.FoodDetailViewDataMapper
import janmokry.kaloricketabulkylite.feature.foodexplorer.presentation.mapper.FoodDetailViewDataMapperImpl
import janmokry.kaloricketabulkylite.feature.foodexplorer.presentation.mapper.FoodListViewDataMapper
import janmokry.kaloricketabulkylite.feature.foodexplorer.presentation.mapper.FoodListViewDataMapperImpl
import janmokry.kaloricketabulkylite.feature.foodexplorer.repository.FoodDetailRepository
import janmokry.kaloricketabulkylite.feature.foodexplorer.repository.FoodDetailRepositoryImpl
import janmokry.kaloricketabulkylite.feature.foodexplorer.repository.FoodExplorerRepository
import janmokry.kaloricketabulkylite.feature.foodexplorer.repository.FoodExplorerRepositoryImpl
import janmokry.kaloricketabulkylite.feature.foodexplorer.repository.api.FoodApi
import janmokry.kaloricketabulkylite.feature.foodexplorer.repository.api.FoodDetailApi
import janmokry.kaloricketabulkylite.feature.foodexplorer.repository.mapper.FoodDetailDataMapper
import janmokry.kaloricketabulkylite.feature.foodexplorer.repository.mapper.FoodDetailDataMapperImpl
import janmokry.kaloricketabulkylite.feature.foodexplorer.repository.mapper.FoodListDataMapper
import janmokry.kaloricketabulkylite.feature.foodexplorer.repository.mapper.FoodListDataMapperImpl
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FoodExplorerModule {

    @Module
    @InstallIn(SingletonComponent::class)
    interface Bindings {

        @Binds
        fun bindFoodExplorerRepository(impl: FoodExplorerRepositoryImpl): FoodExplorerRepository

        @Binds
        fun bindFoodDetailRepository(impl: FoodDetailRepositoryImpl): FoodDetailRepository

        @Binds
        fun bindFoodListDataMapper(impl: FoodListDataMapperImpl): FoodListDataMapper

        @Binds
        fun bindFoodDetailDataMapper(impl: FoodDetailDataMapperImpl): FoodDetailDataMapper

        @Binds
        fun bindFoodListViewDataMapper(impl: FoodListViewDataMapperImpl): FoodListViewDataMapper

        @Binds
        fun bindFoodDetailViewDataMapper(
            impl: FoodDetailViewDataMapperImpl
        ): FoodDetailViewDataMapper

        @Binds
        fun bindGetFoodListUseCase(impl: GetFoodListUseCaseImpl): GetFoodListUseCase

        @Binds
        fun bindGetFoodDetailUseCase(impl: GetFoodDetailUseCaseImpl): GetFoodDetailUseCase
    }

    @Singleton
    @Provides
    fun provideFoodApi(retrofit: Retrofit): FoodApi = retrofit.create(FoodApi::class.java)

    @Singleton
    @Provides
    fun provideFoodDetailApi(retrofit: Retrofit): FoodDetailApi =
        retrofit.create(FoodDetailApi::class.java)
}