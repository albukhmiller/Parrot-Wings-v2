package company.alex.com.parrotwings.ui.presentation.screens.authorization.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import company.alex.com.parrotwings.App
import company.alex.com.parrotwings.App.Companion.component
import company.alex.com.parrotwings.BR
import company.alex.com.parrotwings.R
import company.alex.com.parrotwings.databinding.FragmentLoginBinding
import company.alex.com.parrotwings.di.components.DaggerAppComponent
import company.alex.com.parrotwings.ui.presentation.base.BaseFragment

class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding>() {
    override var layoutId = R.layout.fragment_login
    override var bindingVariable = BR.viewModel
    override var viewModelClass = LoginViewModel::class.java

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       component.inject(this)
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}
