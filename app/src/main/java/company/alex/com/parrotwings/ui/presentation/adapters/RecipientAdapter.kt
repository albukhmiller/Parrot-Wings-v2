package company.alex.com.parrotwings.ui.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import company.alex.com.parrotwings.R
import company.alex.com.parrotwings.databinding.ItemSearchUserBinding
import company.alex.com.parrotwings.domain.model.SearchUser


class RecipientAdapter(private val listener : (SearchUser) -> Unit) :
    BaseAdapter<RecipientAdapter.ReceiverHolder, MutableList<SearchUser>>() {
    private var data = mutableListOf<SearchUser>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceiverHolder {
        var inflater = LayoutInflater.from(parent.context)

        var binding =
            DataBindingUtil.inflate<ItemSearchUserBinding>(
                inflater,
                R.layout.item_search_user,
                parent,
                false
            )

        return ReceiverHolder(binding)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ReceiverHolder, position: Int) = holder.bind(data[position], listener)

    override fun setData(data: MutableList<SearchUser>) {
        this.data = data
        notifyDataSetChanged()
    }

    inner class ReceiverHolder(private val binding: ItemSearchUserBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: SearchUser, listener: (SearchUser) -> Unit) {
            binding.model = user
            binding.root.setOnClickListener { listener(user) }
        }
    }
}