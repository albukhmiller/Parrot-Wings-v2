package company.alex.com.parrotwings.data.remote.repositories.transactions

import company.alex.com.parrotwings.data.remote.request.TransactionRequest
import company.alex.com.parrotwings.data.remote.response.TransactionHistoryResponse
import io.reactivex.Completable
import io.reactivex.Flowable

interface TransactionRepository {
    fun retrieveTransactions(): Flowable<TransactionHistoryResponse>
    fun createTransactions(transaction: TransactionRequest): Completable
}