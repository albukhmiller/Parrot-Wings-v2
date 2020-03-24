package company.alex.com.parrotwings.domain.useCases.authorization.logout

import company.alex.com.parrotwings.data.local.LocalStorageRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(private val localStorageRepository: LocalStorageRepository) {
    operator fun invoke() {
        localStorageRepository.clearToken()
    }
}