package company.alex.com.parrotwings.domain.useCases.user

import company.alex.com.parrotwings.data.remote.repositories.userInfo.UserRepository
import company.alex.com.parrotwings.domain.model.UserInfo
import javax.inject.Inject

class UserInfoUseCase @Inject constructor(private val userRepository: UserRepository) {
    operator fun invoke() =
        userRepository.getUserInfo().map {
            return@map UserInfo(
                it.info.id,
                it.info.name,
                it.info.email,
                it.info.balance
            )
        }
}