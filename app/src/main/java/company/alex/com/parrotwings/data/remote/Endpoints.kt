package company.alex.com.parrotwings.data.remote

class Endpoints {
    companion object {
        const val LOGIN = "/sessions/create/"
        const val REGISTRATION = "/users/"
        const val RETRIEVE_TRANSACTIONS = "/api/protected/transactions/"
        const val CREATE_TRANSACTION = "/api/protected/transactions/"
        const val USER_INFO = "/api/protected/user-info/"

        const val FILTER_USERS ="/api/protected/users/list/"
    }
}