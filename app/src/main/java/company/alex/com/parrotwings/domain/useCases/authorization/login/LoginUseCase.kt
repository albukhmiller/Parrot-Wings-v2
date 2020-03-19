package company.alex.com.parrotwings.domain.useCases.authorization.login

import company.alex.com.parrotwings.data.remote.repositories.authorization.AuthorizationRepository
import company.alex.com.parrotwings.data.remote.request.AuthUserRequest
import company.alex.com.parrotwings.domain.model.AuthUser
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: AuthorizationRepository) {
    operator fun invoke(authUser: AuthUser) =
        repository.login(AuthUserRequest(authUser.username, authUser.password, authUser.email))
}