package company.alex.com.parrotwings.data.remote.repositories.transactions

import company.alex.com.parrotwings.data.remote.response.TransactionHistoryResponse
import io.reactivex.Single

interface TransactionRepository {
    fun retrieveTransactions() : Single<TransactionHistoryResponse>
}