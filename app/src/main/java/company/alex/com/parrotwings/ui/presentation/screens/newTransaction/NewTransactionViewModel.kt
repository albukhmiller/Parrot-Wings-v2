package company.alex.com.parrotwings.ui.presentation.screens.newTransaction

import androidx.databinding.ObservableField
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import company.alex.com.parrotwings.R
import company.alex.com.parrotwings.data.remote.excpetions.BadRequestException
import company.alex.com.parrotwings.domain.model.NewTransaction
import company.alex.com.parrotwings.domain.model.SearchUser
import company.alex.com.parrotwings.domain.model.UserInfo
import company.alex.com.parrotwings.domain.useCases.transaction.NewTransactionUseCase
import company.alex.com.parrotwings.domain.useCases.user.SearchUserUseCase
import company.alex.com.parrotwings.domain.useCases.user.UserInfoUseCase
import company.alex.com.parrotwings.ui.presentation.base.BaseViewModel
import io.reactivex.Emitter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class NewTransactionViewModel @Inject constructor(
    private val newTransactionUseCase: NewTransactionUseCase,
    private val userInfoUseCase: UserInfoUseCase,
    private val searchUserUseCase: SearchUserUseCase
) : BaseViewModel() {

    var userInfo = MutableLiveData<UserInfo>()
    var amount = MutableLiveData<Double>()
    var recipient = MutableLiveData<String>()

    var userSuggestions = MutableLiveData<List<SearchUser>>()

    var isCreateTransactionAvailable = ObservableField<Boolean>(false)
    var isUserSuggestionsVisible = ObservableField<Boolean>(true)

    private var emitter: Emitter<String>? = null
    private lateinit var searchUserRequest: Disposable
    private var output: Observable<String> = Observable.create<String> { emitter ->
        this.emitter = emitter
    }.debounce(1L, TimeUnit.SECONDS)

    init {
        output.subscribe {
            searchUsers(it)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        retrieveBalance()
    }

    fun updateUserSuggestions(value: String) {
        if (value.isEmpty())
            return isUserSuggestionsVisible.set(false)

        userSuggestions.postValue(mutableListOf())

        emitter?.onNext(value)
        isUserSuggestionsVisible.set(true)
    }

    fun createTransaction() {
        hideKeyboard()
        isUserSuggestionsVisible.set(false)

        newTransactionUseCase(NewTransaction(recipient.value!!, amount.value!!))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                navigateBack()
            }, { t ->
                if (t is BadRequestException)
                    showError(R.string.invalidUser)
                else handleExceptions(t)
            })
    }

    private fun retrieveBalance() {
        userInfoUseCase()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                userInfo.value = it
            }, { t ->
                handleExceptions(t)
            })
    }

    private fun searchUsers(filter: String) {
        searchUserRequest = searchUserUseCase(filter)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                if (isUserSuggestionsVisible.get()!!)
                    userSuggestions.value = it
            }, { t -> handleExceptions(t) })
    }
}