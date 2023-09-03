package janmokry.kaloricketabulkylite.core.network.interceptor

import janmokry.kaloricketabulkylite.core.network.UrlApiProvider
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class PidInterceptor @Inject constructor(
    private val urlApiProvider: UrlApiProvider
): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val newHttpUrl = originalRequest.url.newBuilder()
            .addQueryParameter("pid", urlApiProvider.provideApiKey())
            .build()

        val newRequest = originalRequest.newBuilder()
            .url(newHttpUrl)
            .build()

        return chain.proceed(newRequest)
    }
}