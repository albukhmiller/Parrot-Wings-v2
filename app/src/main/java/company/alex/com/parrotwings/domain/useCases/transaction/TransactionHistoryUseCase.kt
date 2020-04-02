package company.alex.com.parrotwings.domain.useCases.transaction

import company.alex.com.parrotwings.data.remote.repositories.transactions.TransactionRepository
import company.alex.com.parrotwings.domain.model.Transaction
import javax.inject.Inject

class TransactionHistoryUseCase @Inject constructor(private val repository: TransactionRepository) {

    operator fun invoke() = repository.retrieveTransactions().map {
        var transactions = mutableListOf<Transaction>()

        it.transactions.forEach { transaction ->
                transactions.add(
                    Transaction(
                        transaction.id,
                        transaction.username,
                        transaction.balance,
                        transaction.amount,
                        transaction.date
                    )
                )
            }
            return@map transactions.toList()
        }
}