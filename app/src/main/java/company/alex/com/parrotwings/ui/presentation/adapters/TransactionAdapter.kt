package company.alex.com.parrotwings.ui.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import company.alex.com.parrotwings.R
import company.alex.com.parrotwings.databinding.ItemTransactionBinding
import company.alex.com.parrotwings.domain.model.Transaction
import org.jetbrains.anko.image

class TransactionAdapter : BaseAdapter<TransactionAdapter.TransactionHolder, MutableList<Transaction>>() {

    private var data: MutableList<Transaction>? = null

    override fun setData(data: MutableList<Transaction>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionHolder {
        var inflater = LayoutInflater.from(parent.context)
        var binding =
            DataBindingUtil.inflate<ItemTransactionBinding>(
                inflater,
                R.layout.item_transaction,
                parent,
                false
            )

        return TransactionHolder(binding)
    }

    override fun getItemCount() = data?.size ?: 0

    override fun onBindViewHolder(holder: TransactionHolder, position: Int) = holder.bind(data!![position])


    inner class TransactionHolder(private val binding: ItemTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(transaction: Transaction) {
            binding.model = transaction

            if (transaction.amount < 0)
                binding.imgTransaction.image = binding.root.context.getDrawable(R.drawable.ic_outgoing_transaction)
            else
                binding.imgTransaction.image = binding.root.context.getDrawable(R.drawable.ic_incoming_transaction)
        }
    }
}