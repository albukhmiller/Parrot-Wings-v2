package company.alex.com.parrotwings.domain.useCases.transaction

import company.alex.com.parrotwings.data.remote.repositories.transactions.TransactionRepository
import company.alex.com.parrotwings.data.remote.request.TransactionRequest
import company.alex.com.parrotwings.domain.model.NewTransaction
import company.alex.com.parrotwings.domain.model.Transaction
import javax.inject.Inject

class NewTransactionUseCase @Inject constructor(private val transactionRepository: TransactionRepository) {

    operator fun invoke(transaction: NewTransaction) = transactionRepository.createTransactions(TransactionRequest(transaction.name,
        transaction.amount))
}