package company.alex.com.parrotwings.data.remote.repositories.userInfo

import company.alex.com.parrotwings.data.remote.response.UserInfoResponse
import io.reactivex.Single

interface UserInfoRepository {
    fun getUserInfo(): Single<UserInfoResponse>
}