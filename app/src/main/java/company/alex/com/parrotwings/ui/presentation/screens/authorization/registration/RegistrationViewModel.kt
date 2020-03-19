package company.alex.com.parrotwings.ui.presentation.screens.authorization.registration

import android.util.Log
import androidx.lifecycle.MutableLiveData
import company.alex.com.parrotwings.R
import company.alex.com.parrotwings.domain.model.AuthUser
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

    fun registration() {
        var authUser = AuthUser(userName.value.orEmpty(), password.value.orEmpty(), email.value.orEmpty())

        registrationUserCase(authUser)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ token ->
                navigateRoot(R.id.mainFragment)
            },
                { error ->
                    Log.d("ERROR", error.message)
                })

    }
}