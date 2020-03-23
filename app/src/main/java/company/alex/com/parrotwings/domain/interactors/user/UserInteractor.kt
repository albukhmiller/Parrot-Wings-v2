package company.alex.com.parrotwings.domain.interactors

import io.reactivex.Single

interface UserInteractor {
    var isUserAuthorized : Single<Boolean>
}