package janmokry.kaloricketabulkylite.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import janmokry.kaloricketabulkylite.core.network.UrlApiProvider
import janmokry.kaloricketabulkylite.network.UrlApiProviderImpl

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    @Binds
    fun bindsUrlApiProvider(impl: UrlApiProviderImpl): UrlApiProvider
}
