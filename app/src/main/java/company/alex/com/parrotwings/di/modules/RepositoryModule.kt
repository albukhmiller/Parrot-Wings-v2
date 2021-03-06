package company.alex.com.parrotwings.di.modules

import company.alex.com.parrotwings.data.local.LocalStorageRepository
import company.alex.com.parrotwings.data.local.LocalStorageRepositoryImpl
import company.alex.com.parrotwings.data.remote.repositories.authorization.AuthorizationRepository
import company.alex.com.parrotwings.data.remote.repositories.authorization.AuthorizationRepositoryImpl
import company.alex.com.parrotwings.data.remote.repositories.transactions.TransactionRepository
import company.alex.com.parrotwings.data.remote.repositories.transactions.TransactionRepositoryImpl
import company.alex.com.parrotwings.data.remote.repositories.userInfo.UserRepository
import company.alex.com.parrotwings.data.remote.repositories.userInfo.UserRepositoryImpl
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

    @Provides
    @Singleton
    fun providesTransactionRepository(transactionRepository: TransactionRepositoryImpl): TransactionRepository =
        transactionRepository

    @Provides
    @Singleton
    fun providesUserInfoRepository(userRepository: UserRepositoryImpl): UserRepository = userRepository


    /*local repositories*/
    @Provides
    @Singleton
    fun providesLocalStorageRepository(localStorage: LocalStorageRepositoryImpl): LocalStorageRepository =
        localStorage
}