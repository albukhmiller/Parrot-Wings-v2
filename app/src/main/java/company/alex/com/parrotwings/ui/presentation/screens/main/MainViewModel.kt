package company.alex.com.parrotwings.ui.presentation.screens.main

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import company.alex.com.parrotwings.domain.model.Transaction
import company.alex.com.parrotwings.ui.presentation.base.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor() : BaseViewModel() {

    var transactions = MutableLiveData<MutableList<Transaction>>()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        transactions.value = mutableListOf(
            Transaction(1, "Test", 12.3, 123.4, "20.03.2019"),
            Transaction(1, "Test", 12.3, 123.4, "20.03.2019"),
            Transaction(1, "Test", 12.3, 123.4, "20.03.2019"),
            Transaction(1, "Test", 12.3, 123.4, "20.03.2019"),
            Transaction(1, "Test", 12.3, 123.4, "20.03.2019"),
            Transaction(1, "Test", 12.3, 123.4, "20.03.2019"),
            Transaction(1, "Test", 12.3, 123.4, "20.03.2019"),
            Transaction(1, "Test", 12.3, 123.4, "20.03.2019")
        )
    }
}