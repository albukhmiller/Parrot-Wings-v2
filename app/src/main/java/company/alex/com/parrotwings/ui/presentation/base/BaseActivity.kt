package company.alex.com.parrotwings.ui.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import company.alex.com.parrotwings.ui.presentation.base.viewModelFactory.ViewModelFactory
import javax.inject.Inject

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: VM

    abstract var viewModelClass: Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = run { ViewModelProvider(this, viewModelFactory)[viewModelClass] }!!

        lifecycle.addObserver(viewModel)
    }
}