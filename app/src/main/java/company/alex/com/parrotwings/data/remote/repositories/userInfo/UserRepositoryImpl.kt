package company.alex.com.parrotwings.data.remote.repositories.userInfo

import company.alex.com.parrotwings.data.remote.ApiServer
import company.alex.com.parrotwings.data.remote.request.SearchUserRequest
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val apiServer: ApiServer) : UserRepository {
    override fun getUserInfo() = apiServer.getUserInfo()
    override fun searchUsers(filter: String) = apiServer.searchUsers(
        SearchUserRequest(filter))
}