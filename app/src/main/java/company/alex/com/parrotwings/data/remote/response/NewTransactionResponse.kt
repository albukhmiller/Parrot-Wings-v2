package company.alex.com.parrotwings.data.remote.response

import com.google.gson.annotations.SerializedName

class NewTransactionResponse(@SerializedName("trans_token") val transaction: TransactionResponse) {
}