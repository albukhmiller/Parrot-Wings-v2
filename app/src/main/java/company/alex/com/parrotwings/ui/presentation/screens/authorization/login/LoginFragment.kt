package company.alex.com.parrotwings.ui.presentation.screens.authorization.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import company.alex.com.parrotwings.App.Companion.component
import company.alex.com.parrotwings.BR
import company.alex.com.parrotwings.R
import company.alex.com.parrotwings.databinding.FragmentLoginBinding
import company.alex.com.parrotwings.ui.presentation.base.BaseFragment
import company.alex.com.parrotwings.ui.presentation.extensions.hideKeyboard
import company.alex.com.parrotwings.utils.Validators
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding>() {
    override var layoutId = R.layout.fragment_login
    override var bindingVariable = BR.viewModel
    override var viewModelClass = LoginViewModel::class.java

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        component.inject(this)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setEmailEditViewListeners()
        setClickListeners()
    }

    private fun setClickListeners() {
        btnLogin.setOnClickListener { view?.hideKeyboard() }
        tvRegistration.setOnClickListener { view?.hideKeyboard() }
    }

    private fun setEmailEditViewListeners() {
        edEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var isValidate = Validators.validateEmail(edEmail)

                if (isValidate)
                    viewModel.isEmailCorrect.set(true)
                else viewModel.isEmailCorrect.set(false)
            }
        })
    }
}
