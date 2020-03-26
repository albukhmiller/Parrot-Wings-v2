package company.alex.com.parrotwings.ui.presentation.screens.profile

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import company.alex.com.parrotwings.domain.model.UserInfo
import company.alex.com.parrotwings.domain.useCases.user.UserInfoUseCase
import company.alex.com.parrotwings.ui.presentation.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val userInfoUseCase: UserInfoUseCase) : BaseViewModel() {

    var userInfo = MutableLiveData<UserInfo>()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        userInfoUseCase()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                userInfo.value = it
            }, { t -> handleExceptions(t) })
    }
}