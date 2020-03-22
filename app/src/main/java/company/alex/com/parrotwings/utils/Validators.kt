package company.alex.com.parrotwings.utils

import android.util.Patterns
import android.widget.EditText
import company.alex.com.parrotwings.R

class Validators {

    companion object {
        fun validateEmail(view: EditText): Boolean {
            if (!Patterns.EMAIL_ADDRESS.matcher(view.text).matches()) {
                view.error = view.context.getString(R.string.emailValidatorError)

                return  false
            }

            view.error = null

            return  true
        }
    }
}