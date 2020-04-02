package company.alex.com.parrotwings.ui.presentation.screens.authorization.login

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import company.alex.com.parrotwings.R
import company.alex.com.parrotwings.domain.model.UserAuth
import company.alex.com.parrotwings.domain.useCases.authorization.login.LoginUseCase
import company.alex.com.parrotwings.ui.presentation.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : BaseViewModel() {

    var login = MutableLiveData<String>()
    var password = MutableLiveData<String>()

    var isLoginAvailable = ObservableField<Boolean>()
    var isEmailCorrect = ObservableField<Boolean>(false)

    fun login() {
        isLoading.set(true)
        hideKeyboard()

        var user = UserAuth(login.value.orEmpty(), password.value.orEmpty())
        loginUseCase(user)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doFinally { isLoading.set(false) }
            .subscribe({
                clearBackStackAndNavigate(R.id.mainFragment)
            }, {
                showError(R.string.invalidLoginOrPassword)
            })
    }

    fun navigateToRegistration() {
        navigateTo(R.id.registrationFragment)
    }

    fun onInputTextChanged() {
        var isFieldsNotEmpty = !login.value.isNullOrEmpty() && !password.value.isNullOrEmpty()

        isLoginAvailable.set(isFieldsNotEmpty && isEmailCorrect.get()!!)
    }
}