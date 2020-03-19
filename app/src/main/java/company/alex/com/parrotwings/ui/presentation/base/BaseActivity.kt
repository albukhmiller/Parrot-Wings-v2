package company.alex.com.parrotwings.ui.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import company.alex.com.parrotwings.ui.presentation.base.viewModelFactory.ViewModelFactory
import javax.inject.Inject

abstract class BaseActivity<VM : BaseViewModel, VB : ViewDataBinding> : AppCompatActivity() {

    private lateinit var viewDataBinding: VB

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: VM

    abstract var layoutId: Int
    abstract var bindingVariable: Int
    abstract var viewModelClass: Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = run { ViewModelProvider(this, viewModelFactory)[viewModelClass] }!!

        viewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        viewDataBinding.setVariable(bindingVariable, viewModel)

        viewDataBinding.lifecycleOwner = this

        lifecycle.addObserver(viewModel)
    }
}