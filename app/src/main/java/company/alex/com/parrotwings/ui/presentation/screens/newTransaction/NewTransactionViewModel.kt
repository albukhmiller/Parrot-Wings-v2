package company.alex.com.parrotwings.ui.presentation.screens.newTransaction

import androidx.databinding.ObservableField
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import company.alex.com.parrotwings.domain.model.NewTransaction
import company.alex.com.parrotwings.domain.model.SearchUser
import company.alex.com.parrotwings.domain.useCases.transaction.NewTransactionUseCase
import company.alex.com.parrotwings.domain.useCases.user.SearchUserUseCase
import company.alex.com.parrotwings.domain.useCases.user.UserInfoUseCase
import company.alex.com.parrotwings.ui.presentation.base.BaseViewModel
import io.reactivex.Emitter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class NewTransactionViewModel @Inject constructor(
    private val newTransactionUseCase: NewTransactionUseCase,
    private val userInfoUseCase: UserInfoUseCase,
    private val searchUserUseCase: SearchUserUseCase
) : BaseViewModel() {
    var balance = MutableLiveData<Double>()
    var amount = MutableLiveData<Double>()
    var recipient = MutableLiveData<String>()

    var isForceHideUserSuggestions: Boolean = false

    var isCreateTransactionAvailable = ObservableField<Boolean>(false)
    var isUserSuggestionsVisible = ObservableField<Boolean>(true)

    var emitter: Emitter<String>? = null

    var userSuggestions = MutableLiveData<MutableList<SearchUser>>()

    private var output: Observable<String> = Observable.create<String> { emitter ->
        this.emitter = emitter
    }.debounce(1L, TimeUnit.SECONDS)

    private val mediator = MediatorLiveData<String>().apply {
        addSource(recipient) { value ->
            setValue(value)

            if (value.isNullOrEmpty())
                return@addSource isUserSuggestionsVisible.set(false)

            if (toggleUserSuggestionsVisibilityFlags())
                emitter?.onNext(value)
            isForceHideUserSuggestions = false
        }
    }.also { it.observeForever { /* empty */ } }

    init {
        output.subscribe {
            searchUsers(it)
        }
    }

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

    private fun searchUsers(filter: String) {
        searchUserUseCase(filter)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                userSuggestions.value = it
            }, { t -> handleExceptions(t) })
    }

    private fun toggleUserSuggestionsVisibilityFlags(): Boolean {

        if (isForceHideUserSuggestions) {
            isUserSuggestionsVisible.set(false)
            return false
        }
        isUserSuggestionsVisible.set(true)

        return true
    }
}