package company.alex.com.parrotwings.data.remote.repositories.userInfo

import company.alex.com.parrotwings.data.remote.response.UserInfoResponse
import io.reactivex.Single

interface UserRepository {
    fun getUserInfo(): Single<UserInfoResponse>
    fun searchUsers(filter: String): Single<MutableList<UserInfoResponse.UserInfo>>
}