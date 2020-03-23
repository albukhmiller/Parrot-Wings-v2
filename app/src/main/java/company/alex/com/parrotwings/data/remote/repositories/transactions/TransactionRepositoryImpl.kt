package company.alex.com.parrotwings.data.remote.repositories.transactions

import company.alex.com.parrotwings.data.remote.ApiServer
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(private val apiServer: ApiServer) : TransactionRepository {
    override fun retrieveTransactions() = apiServer.getTransactions()
}