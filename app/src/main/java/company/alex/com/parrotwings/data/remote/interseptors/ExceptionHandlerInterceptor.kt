package company.alex.com.parrotwings.data.remote.interseptors

import company.alex.com.parrotwings.data.remote.excpetions.AuthorizationException
import company.alex.com.parrotwings.data.remote.excpetions.BadRequestException
import company.alex.com.parrotwings.data.remote.excpetions.InternalErrorException
import okhttp3.Interceptor
import okhttp3.Response

class ExceptionHandlerInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var response = chain.proceed(chain.request())

        when (response.code) {
            400 -> throw  BadRequestException(response.message)
            401 -> throw AuthorizationException(response.message)
            403 -> throw  InternalErrorException(response.message)
        }

        return response
    }
}