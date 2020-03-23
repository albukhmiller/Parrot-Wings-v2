package company.alex.com.parrotwings.ui.presentation.screens.main

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import company.alex.com.parrotwings.domain.model.Transaction
import company.alex.com.parrotwings.domain.useCases.transaction.TransactionHistoryUseCase
import company.alex.com.parrotwings.ui.presentation.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(private  val transactionHistoryUseCase: TransactionHistoryUseCase) : BaseViewModel() {

    var transactions = MutableLiveData<MutableList<Transaction>>()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        transactionHistoryUseCase()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                transactions.value = it
            }, {})

    }
}