package company.alex.com.parrotwings.ui.presentation.screens.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import company.alex.com.parrotwings.App
import company.alex.com.parrotwings.BR
import company.alex.com.parrotwings.R
import company.alex.com.parrotwings.databinding.FragmentProfileBinding
import company.alex.com.parrotwings.ui.presentation.base.BaseFragment

class ProfileFragment : BaseFragment<ProfileViewModel, FragmentProfileBinding>() {
    override var layoutId = R.layout.fragment_profile
    override var bindingVariable = BR.viewModel
    override var viewModelClass = ProfileViewModel::class.java

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        App.component.inject(this)

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
    }
}