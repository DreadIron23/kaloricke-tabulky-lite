package janmokry.kaloricketabulkylite.network

import janmokry.kaloricketabulkylite.BuildConfig
import janmokry.kaloricketabulkylite.core.network.UrlApiProvider
import javax.inject.Inject

class UrlApiProviderImpl @Inject constructor() : UrlApiProvider {

    override fun provideBaseApiUrl() = BuildConfig.BASE_API_URL

    override fun provideApiKey() = BuildConfig.API_KEY
}
