package company.alex.com.parrotwings.ui.presentation.screens.mainActivity

import company.alex.com.parrotwings.domain.interactors.user.UserInteractor
import company.alex.com.parrotwings.ui.presentation.base.BaseViewModel
import javax.inject.Inject

class RootViewModel @Inject constructor(private val userInteractor: UserInteractor) : BaseViewModel() {

    fun isUserAuthorized() = userInteractor.isUserAuthorized()
}