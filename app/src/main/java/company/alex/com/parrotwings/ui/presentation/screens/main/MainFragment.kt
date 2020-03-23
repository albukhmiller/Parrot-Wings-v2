package company.alex.com.parrotwings.ui.presentation.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import company.alex.com.parrotwings.App
import company.alex.com.parrotwings.BR
import company.alex.com.parrotwings.R
import company.alex.com.parrotwings.databinding.FragmentMainBinding
import company.alex.com.parrotwings.ui.presentation.adapters.TransactionAdapter
import company.alex.com.parrotwings.ui.presentation.base.BaseFragment
import company.alex.com.parrotwings.utils.ControlsConfigarator
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseFragment<MainViewModel, FragmentMainBinding>() {

    override var layoutId = R.layout.fragment_main
    override var bindingVariable = BR.viewModel
    override var viewModelClass = MainViewModel::class.java

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        App.component.inject(this)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ControlsConfigarator.configarateRecyclerView(rvTransactions, TransactionAdapter() )
    }
}
