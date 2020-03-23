package company.alex.com.parrotwings.ui.presentation.screens.mainActivity

import android.os.Bundle
import androidx.navigation.Navigation
import company.alex.com.parrotwings.App
import company.alex.com.parrotwings.R
import company.alex.com.parrotwings.ui.presentation.base.BaseActivity

class MainActivity : BaseActivity<RootViewModel>() {
    override var viewModelClass = RootViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        App.component.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        if (viewModel.isUserAuthorized())
            Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.mainFragment)
        else Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.loginFragment)
    }
}