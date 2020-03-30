package company.alex.com.parrotwings.ui.presentation.navigation

import androidx.navigation.fragment.NavHostFragment
import company.alex.com.parrotwings.R

class NavGraphConfigurator {
    companion object {
        fun changeRootFragment(navHostFragment: NavHostFragment, destination: Int) {
            val inflater = navHostFragment.navController.navInflater
            val graph = inflater.inflate(R.navigation.nav_graph)
            graph.startDestination = destination

            navHostFragment.navController.graph = graph
        }
    }
}