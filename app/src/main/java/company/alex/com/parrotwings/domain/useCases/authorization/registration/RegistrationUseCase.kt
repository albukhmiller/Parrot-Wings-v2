package company.alex.com.parrotwings.domain.useCases.authorization.registration

import company.alex.com.parrotwings.data.remote.repositories.authorization.AuthorizationRepository
import company.alex.com.parrotwings.data.remote.request.AuthUserRequest
import company.alex.com.parrotwings.domain.model.UserRegistration
import javax.inject.Inject

class RegistrationUseCase @Inject constructor(private val authorizationRepository: AuthorizationRepository) {

    operator fun invoke(userRegistration: UserRegistration) =
        authorizationRepository.registration(AuthUserRequest(userRegistration.username, userRegistration.password, userRegistration.email))
            .map { result -> return@map result.token }
}