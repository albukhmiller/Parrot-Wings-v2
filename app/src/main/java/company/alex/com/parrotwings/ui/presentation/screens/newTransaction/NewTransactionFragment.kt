package company.alex.com.parrotwings.ui.presentation.screens.newTransaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mancj.materialsearchbar.MaterialSearchBar
import company.alex.com.parrotwings.App
import company.alex.com.parrotwings.BR
import company.alex.com.parrotwings.R
import company.alex.com.parrotwings.databinding.FragmentNewTransactionBinding
import company.alex.com.parrotwings.ui.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_new_transaction.*


class NewTransactionFragment : BaseFragment<NewTransactionViewModel, FragmentNewTransactionBinding>() {
    override var layoutId = R.layout.fragment_new_transaction
    override var bindingVariable = BR.viewModel
    override var viewModelClass = NewTransactionViewModel::class.java

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        App.component.inject(this)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchBar.setOnSearchActionListener(object : MaterialSearchBar.OnSearchActionListener {
            override fun onButtonClicked(buttonCode: Int) {
                var t = 4
            }

            override fun onSearchStateChanged(enabled: Boolean) {
            }

            override fun onSearchConfirmed(text: CharSequence?) {
                var t = 4
            }

        })
        initToolbar()
    }
}
