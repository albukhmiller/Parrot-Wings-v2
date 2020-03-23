package company.alex.com.parrotwings.data.remote.repositories.transactions

import company.alex.com.parrotwings.data.remote.ApiServer
import company.alex.com.parrotwings.data.remote.request.TransactionRequest
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(private val apiServer: ApiServer) : TransactionRepository {

    override fun createTransactions(transaction: TransactionRequest) = apiServer.createTransaction(transaction)

    override fun retrieveTransactions() = apiServer.getTransactions()

}