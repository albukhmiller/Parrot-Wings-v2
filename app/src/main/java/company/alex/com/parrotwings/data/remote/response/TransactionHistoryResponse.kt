package company.alex.com.parrotwings.data.remote.response

import com.google.gson.annotations.SerializedName

data class TransactionHistoryResponse(@SerializedName("trans_token") val transactions: List<TransactionResponse>)