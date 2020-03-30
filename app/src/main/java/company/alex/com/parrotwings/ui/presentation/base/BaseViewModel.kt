package company.alex.com.parrotwings.ui.presentation.base

import androidx.databinding.ObservableField
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import company.alex.com.parrotwings.R
import company.alex.com.parrotwings.data.remote.excpetions.AuthorizationException
import company.alex.com.parrotwings.domain.useCases.authorization.logout.LogoutUseCase
import company.alex.com.parrotwings.ui.presentation.navigation.AlertDialogCommand
import company.alex.com.parrotwings.ui.presentation.navigation.NavigationCommand
import company.alex.com.parrotwings.utils.SingleLiveData
import java.net.ConnectException
import javax.inject.Inject

abstract class BaseViewModel : ViewModel(),
    LifecycleObserver {

    @Inject
    lateinit var logoutUseCase: LogoutUseCase

    val navigationCommand = SingleLiveData<NavigationCommand>()
    val errorHandlerCommand = SingleLiveData<AlertDialogCommand>()

    var isLoading = ObservableField<Boolean>()

    protected fun navigateTo(direction: Int) = navigationCommand.postValue(NavigationCommand.To(direction))
    protected fun navigateTo(direction: Int, data: Any) =
        navigationCommand.postValue(NavigationCommand.ToWithData(direction, data))

    protected fun clearBackStackAndNavigate(destination: Int) =
        navigationCommand.postValue(NavigationCommand.ChangeRootDestination(destination))

    protected fun navigateBackTo(destinationId: Int) =
        navigationCommand.postValue(NavigationCommand.BackTo(destinationId))

    protected fun navigateRoot(navHostFragment: Int) =
        navigationCommand.postValue(NavigationCommand.ToRoot(navHostFragment))

    protected fun navigateBack() = navigationCommand.postValue(NavigationCommand.Back)

    protected fun showError(message: String) = errorHandlerCommand.postValue(AlertDialogCommand.ShowError(message))
    protected fun showError(messageId: Int) = errorHandlerCommand.postValue(AlertDialogCommand.ShowErrorById(messageId))
    protected fun showDialog(messageId: Int) =
        errorHandlerCommand.postValue(AlertDialogCommand.ShowDialog(messageId) { logout() })

    protected fun handleExceptions(t: Throwable?) {
        when (t) {
            is AuthorizationException -> showDialog(R.string.unauthorizedError)
            is ConnectException -> showError(R.string.noConnectionInternet)
            else -> showError(t?.message!!)
        }
    }

    private fun logout() {
        logoutUseCase()
        clearBackStackAndNavigate(R.id.loginFragment)
    }
}