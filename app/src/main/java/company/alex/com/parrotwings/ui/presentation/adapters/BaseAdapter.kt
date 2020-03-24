package company.alex.com.parrotwings.ui.presentation.adapters

import androidx.recyclerview.widget.RecyclerView
import company.alex.com.parrotwings.ui.presentation.bindingAdapters.BindableListAdapter

abstract class BaseAdapter<T : RecyclerView.ViewHolder, D : Collection<*>?> : RecyclerView.Adapter<T>(),
    BindableListAdapter<D> {
}