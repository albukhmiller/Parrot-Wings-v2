package company.alex.com.parrotwings.di.modules

import company.alex.com.parrotwings.BuildConfig
import company.alex.com.parrotwings.data.local.LocalStorageRepository
import company.alex.com.parrotwings.data.remote.ApiServer
import company.alex.com.parrotwings.data.remote.interseptors.ExceptionHandlerInterceptor
import company.alex.com.parrotwings.data.remote.interseptors.RequestInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(localStorageRepository: LocalStorageRepository): ApiServer = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(
            OkHttpClient.Builder()
                .addInterceptor(ExceptionHandlerInterceptor())
                .addInterceptor(RequestInterceptor(localStorageRepository))
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
        )
        .build()
        .create(ApiServer::class.java)
}