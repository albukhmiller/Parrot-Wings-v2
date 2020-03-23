package company.alex.com.parrotwings.ui.bindingAdapters

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

class CustomBindingAdapters {

    companion object {
        @JvmStatic
        @BindingAdapter("app:bindingVisibility")
        fun visibilityAdapter(view: View, isVisible: Boolean) {
            if (isVisible)
                view.visibility = View.VISIBLE
            else view.visibility = View.GONE
        }

        @JvmStatic
        @BindingAdapter("app:data")
        fun <T> bindRecyclerViewAdapter(rv: RecyclerView, data: T) {
            if(data == null) return

            if (rv.adapter is BindableListAdapter<*>) {
                (rv.adapter as BindableListAdapter<T>).setData(data)
            }
        }

    }
}