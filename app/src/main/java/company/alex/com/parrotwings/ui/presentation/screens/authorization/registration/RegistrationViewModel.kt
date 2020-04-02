package company.alex.com.parrotwings.ui.presentation.screens.authorization.registration

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import company.alex.com.parrotwings.R
import company.alex.com.parrotwings.domain.model.UserRegistration
import company.alex.com.parrotwings.domain.useCases.authorization.registration.RegistrationUseCase
import company.alex.com.parrotwings.ui.presentation.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(private val registrationUserCase: RegistrationUseCase) :
    BaseViewModel() {

    var userName = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    var email = MutableLiveData<String>()

    var isEmailCorrect = ObservableField<Boolean>(false)
    var isRegistrationAvailable = ObservableField<Boolean>(false)

    fun registration() {
        hideKeyboard()
        
        var user = UserRegistration(userName.value.orEmpty(), password.value.orEmpty(), email.value.orEmpty())

        registrationUserCase(user)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                clearBackStackAndNavigate(R.id.mainFragment)
            },
                {
                    showError(R.string.userAlreadyExists)
                })
    }

    fun onInputTextChanged() {
        var isFieldsNotEmpty =
            !userName.value.isNullOrEmpty() && !password.value.isNullOrEmpty() && !email.value.isNullOrEmpty()


        isRegistrationAvailable.set(isFieldsNotEmpty && isEmailCorrect.get()!!)
    }
}