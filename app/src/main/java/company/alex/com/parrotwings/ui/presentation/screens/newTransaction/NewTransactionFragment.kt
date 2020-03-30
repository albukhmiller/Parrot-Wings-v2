package company.alex.com.parrotwings.ui.presentation.screens.newTransaction

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import company.alex.com.parrotwings.App
import company.alex.com.parrotwings.BR
import company.alex.com.parrotwings.R
import company.alex.com.parrotwings.databinding.FragmentNewTransactionBinding
import company.alex.com.parrotwings.ui.presentation.adapters.RecipientAdapter
import company.alex.com.parrotwings.ui.presentation.base.BaseFragment
import company.alex.com.parrotwings.utils.ControlsConfigurator
import company.alex.com.parrotwings.utils.Validators
import kotlinx.android.synthetic.main.fragment_new_transaction.*
import kotlinx.android.synthetic.main.search_user_layout.*


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

        initToolbar()

        configureAmountEditView()
        configureRecipientEditView()
    }

    private fun configureRecipientEditView() {
        edRecipient.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Validators.validateEmptyString(edRecipient)
            }
        })

        var adapter = RecipientAdapter {
            viewModel.recipient.value = it.name
            viewModel.isForceHideUserSuggestions = true
        }

        ControlsConfigurator.configurateRecyclerView(rvRecipient, adapter)
        viewModel.userSuggestions.observe(this, Observer {
            adapter.setData(it)
        })
    }

    private fun configureAmountEditView() {
        edAmount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.isCreateTransactionAvailable.set(
                    Validators.validateEmptyString(edAmount) &&
                            Validators.validateExceedBalance(edAmount, viewModel.balance.value!!)
                )
            }
        })
    }
}
