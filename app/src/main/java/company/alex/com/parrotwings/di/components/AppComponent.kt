package company.alex.com.parrotwings.di.components

import company.alex.com.parrotwings.di.modules.AppModule
import company.alex.com.parrotwings.di.modules.NetworkModule
import company.alex.com.parrotwings.di.modules.RepositoryModule
import company.alex.com.parrotwings.di.modules.ViewModelModule
import company.alex.com.parrotwings.ui.presentation.screens.authorization.login.LoginFragment
import company.alex.com.parrotwings.ui.presentation.screens.authorization.registration.RegistrationFragment
import company.alex.com.parrotwings.ui.presentation.screens.main.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class,
        ViewModelModule::class,
        RepositoryModule::class,
        NetworkModule::class]
)
interface AppComponent {

    fun inject(mainFragment: MainFragment)
    fun inject(loginFragment: LoginFragment)
    fun inject(registrationFragment: RegistrationFragment)

}