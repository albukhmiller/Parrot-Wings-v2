package company.alex.com.parrotwings.ui.presentation.screens.authorization.registration

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import company.alex.com.parrotwings.App
import company.alex.com.parrotwings.BR
import company.alex.com.parrotwings.R
import company.alex.com.parrotwings.databinding.FragmentRegistationBinding
import company.alex.com.parrotwings.ui.presentation.base.BaseFragment
import company.alex.com.parrotwings.utils.Validators
import kotlinx.android.synthetic.main.fragment_login.*

class RegistrationFragment : BaseFragment<RegistrationViewModel, FragmentRegistationBinding>() {
    override var layoutId = R.layout.fragment_registation
    override var bindingVariable = BR.viewModel
    override var viewModelClass = RegistrationViewModel::class.java

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        App.component.inject(this)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setEmailEditViewListeners()
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
