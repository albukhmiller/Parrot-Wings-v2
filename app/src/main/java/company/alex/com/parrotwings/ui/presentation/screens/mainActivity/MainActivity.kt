package company.alex.com.parrotwings.ui.presentation.screens.mainActivity

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import company.alex.com.parrotwings.App
import company.alex.com.parrotwings.R
import company.alex.com.parrotwings.ui.presentation.base.BaseActivity
import company.alex.com.parrotwings.ui.presentation.navigation.NavGraphConfigurator
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity<RootViewModel>() {
    override var viewModelClass = RootViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        App.component.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        if (savedInstanceState != null) return

        if (viewModel.isUserAuthorized())
            NavGraphConfigurator.changeRootFragment(nav_host_fragment as NavHostFragment, R.id.mainFragment)
        else
            NavGraphConfigurator.changeRootFragment(nav_host_fragment as NavHostFragment, R.id.loginFragment)
    }

    override fun onBackPressed() {
        if (isRootFragment())
            moveTaskToBack(true)
        else
            super.onBackPressed()
    }

    private fun isRootFragment(): Boolean {
        val navHostFragment = nav_host_fragment as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.nav_graph)

        return graph.startDestination == navHostFragment.navController.currentDestination?.id
    }
}
