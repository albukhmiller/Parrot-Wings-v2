package company.alex.com.parrotwings.ui.presentation.bindingAdapters

import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.RecyclerView
import company.alex.com.parrotwings.R
import org.jetbrains.anko.textColor


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
            if (data == null) return

            if (rv.adapter is BindableListAdapter<*>) {
                (rv.adapter as BindableListAdapter<T>).setData(data)
            }
        }

        @JvmStatic
        @InverseBindingAdapter(attribute = "app:text")
        fun getText(view: EditText) = ObservableField<Double>(view.text.toString().toDouble())


        @JvmStatic
        @BindingAdapter("app:text")
        fun setText(view: EditText, value: Double) {
            if (view.text != null
                && view.text.toString().isNotEmpty()
                && view.text.toString().toDouble() != value
            ) {
                view.setText(value.toString())
            }
        }

        @JvmStatic
        @BindingAdapter(
            "app:textChangedEvent",
            requireAll = false
        )
        fun setOnTextChangedListener(
            editText: EditText,
            bindingListener: InverseBindingListener?
        ) {
            if (bindingListener != null)
                editText.addTextChangedListener {
                    bindingListener.onChange()
                }
        }

        @JvmStatic
        @InverseBindingAdapter(
            attribute = "app:bindableText",
            event = "app:textChangedEvent"
        )

        fun bindDoubleToEditTextInverse(editText: EditText): Double? {
            if (editText.text.isNullOrEmpty()) return null

            return try {
                editText.text.toString().toDouble()
            } catch (ex: NumberFormatException) {
                0.0
            }
        }

        @JvmStatic
        @BindingAdapter("app:bindableText")
        fun bindDoubleToEditText(
            editText: EditText,
            newValue: Double
        ) {
            if (editText.text.isNullOrEmpty()) return

            if (editText.text.toString().toDouble() != newValue)
                editText.setText(newValue.toString())
        }

        @JvmStatic
        @BindingAdapter("app:colorTextAmount")
        fun colorTextAmountAdapter(
            textView: TextView,
            amount: Double
        ) {
            if (amount < 0)
                textView.textColor = ContextCompat.getColor(textView.context, R.color.red)
            else textView.textColor = ContextCompat.getColor(textView.context, R.color.green)
        }
    }
}