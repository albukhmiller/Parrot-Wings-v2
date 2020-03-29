package company.alex.com.parrotwings.ui.presentation.screens.mainActivity

import company.alex.com.parrotwings.R
import company.alex.com.parrotwings.domain.interactors.UserInteractor
import company.alex.com.parrotwings.ui.presentation.base.BaseViewModel
import javax.inject.Inject

class RootViewModel @Inject constructor(private val userInteractor: UserInteractor) : BaseViewModel() {

    fun loadRootScreen() {
        if (isUserAuthorized()) {
            navigateTo(R.id.mainFragment)
        } else {
            clearBackStackAndNavigate(R.id.loginFragment)
            navigateRoot(R.id.nav_host_fragment)
        }

    }

    private fun isUserAuthorized() = userInteractor.isUserAuthorized()
}