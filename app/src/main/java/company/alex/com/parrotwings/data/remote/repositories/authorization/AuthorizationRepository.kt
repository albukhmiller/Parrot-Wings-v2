package company.alex.com.parrotwings.data.remote.repositories.authorization

import company.alex.com.parrotwings.data.remote.request.AuthUserRequest
import company.alex.com.parrotwings.data.remote.response.AuthUserResponse
import io.reactivex.Single

interface AuthorizationRepository {

    fun login(authUserRequest: AuthUserRequest): Single<AuthUserResponse>
    fun registration(authUserRequest: AuthUserRequest): Single<AuthUserResponse>
}