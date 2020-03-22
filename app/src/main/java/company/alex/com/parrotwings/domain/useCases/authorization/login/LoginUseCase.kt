package company.alex.com.parrotwings.domain.useCases.authorization.login

import company.alex.com.parrotwings.data.remote.repositories.authorization.AuthorizationRepository
import company.alex.com.parrotwings.data.remote.request.AuthUserRequest
import company.alex.com.parrotwings.domain.model.UserAuth
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: AuthorizationRepository) {
    operator fun invoke(userAuth: UserAuth) =
        repository.login(AuthUserRequest(email = userAuth.login, password = userAuth.password))
}