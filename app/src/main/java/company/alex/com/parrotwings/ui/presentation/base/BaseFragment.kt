package company.alex.com.parrotwings.ui.presentation.base

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import company.alex.com.parrotwings.R
import company.alex.com.parrotwings.ui.presentation.base.viewModelFactory.ViewModelFactory
import company.alex.com.parrotwings.ui.presentation.extensions.hideKeyboard
import company.alex.com.parrotwings.ui.presentation.navigation.AlertDialogCommand
import company.alex.com.parrotwings.ui.presentation.navigation.NavGraphConfigurator.Companion.changeRootFragment
import company.alex.com.parrotwings.ui.presentation.navigation.NavigationCommand
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_profile.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.okButton
import org.jetbrains.anko.support.v4.alert
import javax.inject.Inject

abstract class BaseFragment<VM : BaseViewModel, VB : ViewDataBinding> : Fragment() {
    private lateinit var viewDataBinding: VB

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: VM

    abstract var layoutId: Int
    abstract var bindingVariable: Int
    abstract var viewModelClass: Class<VM>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this, viewModelFactory)[viewModelClass]

        viewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        viewDataBinding.setVariable(bindingVariable, viewModel)

        viewDataBinding.lifecycleOwner = this

        lifecycle.addObserver(viewModel)

        initErrorHandler(viewDataBinding.root)

        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initNavigation()
    }

    protected fun initToolbar() {
        activity?.setActionBar(toolbar)
        activity?.actionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun initNavigation() {
        viewModel.navigationCommand.observe(this) { command ->
            run {
                when (command) {
                    is NavigationCommand.Back -> findNavController().popBackStack()
                    is NavigationCommand.ToRoot -> findNavController().navigate(command.navHostFragment)
                    is NavigationCommand.To -> findNavController().navigate(command.direction)
                    is NavigationCommand.BackTo -> findNavController().popBackStack(command.destinationId, true)
                    is NavigationCommand.ChangeRootDestination -> changeRootFragment(
                        activity?.nav_host_fragment as NavHostFragment,
                        command.destination
                    )
                    is NavigationCommand.ToWithData -> {
                        var bundle = Bundle()
                        bundle.putParcelable("nav", command.data as Parcelable)
                        findNavController().navigate(command.direction, bundle)
                    }
                }
                view?.hideKeyboard()
            }
        }
    }

    private fun initErrorHandler(view: View) {
        viewModel.errorHandlerCommand.observe(this) { command ->
            when (command) {
                is AlertDialogCommand.ShowError -> view.snackbar(command.message)
                is AlertDialogCommand.ShowErrorById -> view.snackbar(command.messageId)
                is AlertDialogCommand.ShowDialog -> alert(getString(command.message), getString(R.string.error)) {
                    okButton { command.okAction() }.also { context?.setTheme(R.style.AlertDialog) }
                }.show()
            }
        }
    }
}