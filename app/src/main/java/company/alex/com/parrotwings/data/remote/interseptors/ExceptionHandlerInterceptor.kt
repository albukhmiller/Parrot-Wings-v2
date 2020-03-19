package company.alex.com.parrotwings.data.remote.interseptors

import okhttp3.Interceptor
import okhttp3.Response

class ExceptionHandlerInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var response = chain.proceed(chain.request())

        when (response.code) {
            400 -> throw  Exception("Invalid input")
            403 -> throw  Exception("Invalid auth token")
        }

        return response
    }
}