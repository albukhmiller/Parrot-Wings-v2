package company.alex.com.parrotwings.ui.presentation.screens.main

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import company.alex.com.parrotwings.R
import company.alex.com.parrotwings.domain.model.Transaction
import company.alex.com.parrotwings.domain.useCases.authorization.logout.LogoutUseCase
import company.alex.com.parrotwings.domain.useCases.transaction.TransactionHistoryUseCase
import company.alex.com.parrotwings.domain.useCases.user.UserInfoUseCase
import company.alex.com.parrotwings.ui.presentation.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val transactionHistoryUseCase: TransactionHistoryUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val userInfoUseCase: UserInfoUseCase
) : BaseViewModel() {

    var transactions = MutableLiveData<MutableList<Transaction>>()
    var balance = MutableLiveData<Double>()

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        retrieveTransactionHistory()
        retrieveBalance()
    }

    fun logout() {
        logoutUseCase()
        navigateTo(R.id.loginFragment)
    }

    fun showProfile() {
        navigateTo(R.id.profileFragment)
    }


    fun createTransaction() {
        navigateTo(R.id.newTransactionFragment)
    }

    private fun retrieveTransactionHistory() {
        transactionHistoryUseCase()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                transactions.value = it.asReversed()
            }, { error ->
                handleExceptions(error)
            })
    }

    private fun retrieveBalance() {
        userInfoUseCase()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                balance.value = it.balance
            }, { error -> handleExceptions(error) })
    }
}