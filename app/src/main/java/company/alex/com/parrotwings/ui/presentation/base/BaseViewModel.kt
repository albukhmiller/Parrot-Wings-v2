package company.alex.com.parrotwings.ui.presentation.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import company.alex.com.parrotwings.ui.presentation.navigation.NavigationCommand
import company.alex.com.parrotwings.utils.SingleLiveData

abstract class BaseViewModel : ViewModel(), LifecycleObserver {

    val navigationCommand = SingleLiveData<NavigationCommand>()

    protected fun navigateTo(direction: Int) = navigationCommand.postValue(NavigationCommand.To(direction))
    protected fun navigateTo(direction: Int, data: Any) =
        navigationCommand.postValue(NavigationCommand.ToWithData(direction, data))

    protected fun navigateBackTo(destinationId: Int) =
        navigationCommand.postValue(NavigationCommand.BackTo(destinationId))

    protected fun navigateRoot(navHostFragment: Int) = navigationCommand.postValue(NavigationCommand.ToRoot(navHostFragment))
    protected fun navigateBack() = navigationCommand.postValue(NavigationCommand.Back)

}