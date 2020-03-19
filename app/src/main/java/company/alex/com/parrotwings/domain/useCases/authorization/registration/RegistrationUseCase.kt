package company.alex.com.parrotwings.domain.useCases.authorization.registration

import company.alex.com.parrotwings.data.remote.repositories.authorization.AuthorizationRepository
import company.alex.com.parrotwings.data.remote.request.AuthUserRequest
import company.alex.com.parrotwings.domain.model.AuthUser
import javax.inject.Inject

class RegistrationUseCase @Inject constructor(private val authorizationRepository: AuthorizationRepository) {

    operator fun invoke(authUser: AuthUser) =
        authorizationRepository.registration(AuthUserRequest(authUser.username, authUser.password, authUser.email))
            .map { result -> return@map result.token }
}