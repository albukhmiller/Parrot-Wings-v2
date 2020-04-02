package company.alex.com.parrotwings.ui.presentation.navigation

sealed class NavigationCommand {
    data class To(val direction: Int) : NavigationCommand()
    data class BackTo(val destinationId: Int) : NavigationCommand()
    data class ToWithData(val direction: Int, val data: Any) : NavigationCommand()
    data class ToRoot(val navHostFragment: Int) : NavigationCommand()
    data class ChangeRootDestination(val destination: Int) : NavigationCommand()

    object Back : NavigationCommand()
    object HideKeyboard : NavigationCommand()
}