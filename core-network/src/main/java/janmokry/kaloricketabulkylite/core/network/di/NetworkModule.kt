package janmokry.kaloricketabulkylite.core.network.di

import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import janmokry.kaloricketabulkylite.core.network.UrlApiProvider
import janmokry.kaloricketabulkylite.core.network.calladapter.SuspendResultCallAdapterFactory
import janmokry.kaloricketabulkylite.core.network.interceptor.PidInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        urlApiProvider: UrlApiProvider,
        resultCallAdapterFactory: SuspendResultCallAdapterFactory
    ): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(urlApiProvider.provideBaseApiUrl())
            .addCallAdapterFactory(resultCallAdapterFactory)
            .addConverterFactory(
                TikXmlConverterFactory.create(
                    TikXml.Builder()
                        .exceptionOnUnreadXml(false)
                        .build()
                ))
            .build()

    @Singleton
    @Provides
    fun provideOkhttpClient(
        pidInterceptor: PidInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(pidInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()
}
