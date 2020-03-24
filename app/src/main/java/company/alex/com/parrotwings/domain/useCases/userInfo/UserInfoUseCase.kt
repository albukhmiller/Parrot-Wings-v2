package company.alex.com.parrotwings.domain.useCases.userInfo

import company.alex.com.parrotwings.data.remote.repositories.userInfo.UserInfoRepository
import company.alex.com.parrotwings.domain.model.UserInfo
import javax.inject.Inject

class UserInfoUseCase @Inject constructor(private val userInfoRepository: UserInfoRepository) {
    operator fun invoke() =
        userInfoRepository.getUserInfo().map {
            return@map UserInfo(
                it.info.id,
                it.info.name,
                it.info.email,
                it.info.balance
            )
        }
}