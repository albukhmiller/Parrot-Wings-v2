package company.alex.com.parrotwings.ui.presentation.screens.newTransaction

import androidx.databinding.ObservableField
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import company.alex.com.parrotwings.domain.model.NewTransaction
import company.alex.com.parrotwings.domain.useCases.transaction.NewTransactionUseCase
import company.alex.com.parrotwings.domain.useCases.userInfo.UserInfoUseCase
import company.alex.com.parrotwings.ui.presentation.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewTransactionViewModel @Inject constructor(
    private val newTransactionUseCase: NewTransactionUseCase,
    private val userInfoUseCase: UserInfoUseCase
) :
    BaseViewModel() {

    var balance = MutableLiveData<Double>()
    var amount = MutableLiveData<Double>()
    var recipient = MutableLiveData<String>()

    var isCreateTransactionAvailable = ObservableField<Boolean>(false)


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        retrieveBalance()
    }

    fun createTransaction() {
        newTransactionUseCase(NewTransaction(recipient.value!!, amount.value!!))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                navigateBack()
            }, { t -> handleExceptions(t) })
    }

    private fun retrieveBalance() {
        userInfoUseCase()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                balance.value = it.balance
            }, { t -> handleExceptions(t) })
    }
}