package company.alex.com.parrotwings.data.remote.response

import com.google.gson.annotations.SerializedName

data class UserInfoResponse(@SerializedName("user_info_token") var info: UserInfo) {
    inner class UserInfo(val id: Long, val name: String, val email: String, val balance: Double)
}