package company.alex.com.parrotwings.ui.presentation.navigation

sealed class AlertDialogCommand {
    data class ShowError(val message: String) : AlertDialogCommand()
    data class ShowErrorById(val messageId: Int) : AlertDialogCommand()
    data class ShowDialog(val message: Int, val okAction: () -> Unit) : AlertDialogCommand()
}