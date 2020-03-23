package company.alex.com.parrotwings.domain.interactors

import company.alex.com.parrotwings.data.local.LocalStorageRepository
import company.alex.com.parrotwings.domain.interactors.user.UserInteractor
import javax.inject.Inject

class UserInteractorImpl @Inject constructor(private val localStorageRepository: LocalStorageRepository) :
    UserInteractor {
    override fun isUserAuthorized(): Boolean {
        var token = localStorageRepository.getToken()
        if (token.isNotEmpty())
            return true

        return false
    }
}