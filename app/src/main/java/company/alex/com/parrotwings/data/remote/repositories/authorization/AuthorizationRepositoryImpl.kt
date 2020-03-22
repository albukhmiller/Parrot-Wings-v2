package company.alex.com.parrotwings.data.remote.repositories.authorization

import company.alex.com.parrotwings.data.local.LocalStorageRepository
import company.alex.com.parrotwings.data.remote.ApiServer
import company.alex.com.parrotwings.data.remote.request.AuthUserRequest
import javax.inject.Inject

class AuthorizationRepositoryImpl @Inject constructor(
    private val apiServer: ApiServer,
    private val localStorage: LocalStorageRepository
) : AuthorizationRepository {

    override fun login(authUserRequest: AuthUserRequest) =
        apiServer.login(authUserRequest).doOnSuccess {
            localStorage.saveToken(it.token)
        }

    override fun registration(authUserRequest: AuthUserRequest) =
        apiServer.registration(authUserRequest).doOnSuccess {
            localStorage.saveToken(it.token)
        }
}
