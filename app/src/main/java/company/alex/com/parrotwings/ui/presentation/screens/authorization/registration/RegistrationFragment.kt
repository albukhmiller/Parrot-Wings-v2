package company.alex.com.parrotwings.ui.presentation.screens.authorization.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import company.alex.com.parrotwings.App
import company.alex.com.parrotwings.BR
import company.alex.com.parrotwings.R
import company.alex.com.parrotwings.databinding.FragmentRegistationBinding
import company.alex.com.parrotwings.ui.presentation.base.BaseFragment

class RegistrationFragment : BaseFragment<RegistrationViewModel, FragmentRegistationBinding>() {
    override var layoutId = R.layout.fragment_registation
    override var bindingVariable = BR.viewModel
    override var viewModelClass = RegistrationViewModel::class.java

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        App.component.inject(this)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

}
