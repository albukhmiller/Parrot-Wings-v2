package company.alex.com.parrotwings.data.local

import android.content.SharedPreferences
import javax.inject.Inject

class LocalStorageRepositoryImpl @Inject constructor(private val sharedPreferences: SharedPreferences) :
    LocalStorageRepository {

    companion object {
        const val TOKEN = "token"
    }

    override fun getToken(): String {
        val token = sharedPreferences.getString(TOKEN, "")
        return token.orEmpty()
    }

    override fun saveToken(token: String) {
        sharedPreferences.edit()
            .putString(TOKEN, token)
            .apply()
    }

    override fun clearToken() {
        sharedPreferences
            .edit()
            .remove(TOKEN)
            .apply()
    }
}