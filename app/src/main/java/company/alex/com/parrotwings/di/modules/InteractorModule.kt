package company.alex.com.parrotwings.di.modules

import company.alex.com.parrotwings.domain.interactors.UserInteractor
import company.alex.com.parrotwings.domain.interactors.UserInteractorImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InteractorModule {

    @Singleton
    @Provides
    fun providesUserInteractor(userInteractor: UserInteractorImpl): UserInteractor = userInteractor
}