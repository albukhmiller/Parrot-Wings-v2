package company.alex.com.parrotwings.di.modules

import company.alex.com.parrotwings.data.local.LocalStorageRepository
import company.alex.com.parrotwings.data.local.LocalStorageRepositoryImpl
import company.alex.com.parrotwings.data.remote.repositories.authorization.AuthorizationRepository
import company.alex.com.parrotwings.data.remote.repositories.authorization.AuthorizationRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RepositoryModule {

    /*remote repositories*/
    @Provides
    @Singleton
    fun providesAuthorizationRepository(authorizationRepository: AuthorizationRepositoryImpl): AuthorizationRepository =
        authorizationRepository


    /*local repositories*/
    @Provides
    @Singleton
    fun providesLocalStorageRepository(localStorage: LocalStorageRepositoryImpl): LocalStorageRepository =
        localStorage
}