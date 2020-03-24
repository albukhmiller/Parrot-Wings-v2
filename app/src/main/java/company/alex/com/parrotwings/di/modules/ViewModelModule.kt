package company.alex.com.parrotwings.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import company.alex.com.parrotwings.di.scopes.ViewModelKey
import company.alex.com.parrotwings.ui.presentation.base.viewModelFactory.ViewModelFactory
import company.alex.com.parrotwings.ui.presentation.screens.authorization.login.LoginViewModel
import company.alex.com.parrotwings.ui.presentation.screens.authorization.registration.RegistrationViewModel
import company.alex.com.parrotwings.ui.presentation.screens.main.MainViewModel
import company.alex.com.parrotwings.ui.presentation.screens.mainActivity.RootViewModel
import company.alex.com.parrotwings.ui.presentation.screens.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun providesViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun providesMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun providesLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegistrationViewModel::class)
    internal abstract fun providesRegistrationViewModel(registrationViewModel: RegistrationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RootViewModel::class)
    internal abstract fun providesRootViewModel(rootViewModel: RootViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    internal abstract fun providesProfileViewModel(profileViewModel: ProfileViewModel): ViewModel
}