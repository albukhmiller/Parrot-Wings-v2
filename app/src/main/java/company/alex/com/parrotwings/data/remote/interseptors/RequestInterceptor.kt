package company.alex.com.parrotwings.data.remote.interseptors

import company.alex.com.parrotwings.data.local.LocalStorageRepository
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class RequestInterceptor @Inject constructor(private val localStorageRepository: LocalStorageRepository) : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        var token = localStorageRepository.getToken()

        if (token.isNullOrEmpty()) return chain.proceed(chain.request())

        return chain.proceed(
            chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        )
    }
}