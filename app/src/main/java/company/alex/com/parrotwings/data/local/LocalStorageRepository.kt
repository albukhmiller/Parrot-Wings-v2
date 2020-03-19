package company.alex.com.parrotwings.data.local

interface LocalStorageRepository {

    fun saveToken(token: String)
    fun getToken(): String
}