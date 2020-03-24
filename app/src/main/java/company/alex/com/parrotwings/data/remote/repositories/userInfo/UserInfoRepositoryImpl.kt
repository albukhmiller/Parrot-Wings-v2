package company.alex.com.parrotwings.data.remote.repositories.userInfo

import company.alex.com.parrotwings.data.remote.ApiServer
import javax.inject.Inject

class UserInfoRepositoryImpl @Inject constructor(private val apiServer: ApiServer) : UserInfoRepository {
    override fun getUserInfo() = apiServer.getUserInfo()
}