package janmokry.kaloricketabulkylite.core.network

interface UrlApiProvider {

    fun provideBaseApiUrl(): String

    fun provideApiKey(): String
}