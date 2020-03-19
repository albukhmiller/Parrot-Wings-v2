package company.alex.com.parrotwings.data.remote.response

import com.google.gson.annotations.SerializedName

data class AuthUserResponse(@SerializedName("id_token") val token: String)