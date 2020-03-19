package company.alex.com.parrotwings.ui.presentation.screens.authorization.login

import company.alex.com.parrotwings.R
import company.alex.com.parrotwings.ui.presentation.base.BaseViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor() : BaseViewModel() {


    fun navigateToRegistration() {
        navigateTo(R.id.registrationFragment)
    }
}