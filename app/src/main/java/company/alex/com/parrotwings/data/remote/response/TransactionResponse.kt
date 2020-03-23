package company.alex.com.parrotwings.data.remote.response

data class TransactionResponse(
    val id: Long,
    val username: String,
    val balance: Double,
    val amount: Double,
    val date: String
)