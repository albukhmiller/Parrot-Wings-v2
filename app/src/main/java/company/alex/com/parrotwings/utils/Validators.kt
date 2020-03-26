package company.alex.com.parrotwings.utils

import android.util.Patterns
import android.widget.EditText
import company.alex.com.parrotwings.R

class Validators {

    companion object {
        fun validateEmail(view: EditText): Boolean {
            if (!Patterns.EMAIL_ADDRESS.matcher(view.text).matches()) {
                view.error = view.context.getString(R.string.emailValidatorError)

                return false
            }

            view.error = null

            return true
        }

        fun validateExceedBalance(view: EditText, balance: Double): Boolean {
            if (view.text.isNullOrEmpty()) return false

            var value = view.text.toString().toDouble()

            if (value < 0 || value > balance) {
                view.error = view.context.getString(R.string.exceedsBalanceError)
                return false
            }

            return true
        }

        fun validateEmptyString(view: EditText): Boolean {
            if (view.text.isNullOrEmpty()) {
                view.error = view.context.getString(R.string.requiredField)

                return false
            }

            return true

        }
    }
}