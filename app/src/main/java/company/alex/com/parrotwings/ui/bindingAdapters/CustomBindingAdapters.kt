package company.alex.com.parrotwings.ui.bindingAdapters

import android.view.View
import androidx.databinding.BindingAdapter

class CustomBindingAdapters {

    companion object {
        @JvmStatic
        @BindingAdapter("app:bindingVisibility")
        fun visibilityAdapter(view: View, isVisible: Boolean) {
            if (isVisible)
                view.visibility = View.VISIBLE
            else view.visibility = View.GONE

        }
    }
}