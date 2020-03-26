package company.alex.com.parrotwings.domain.useCases.user

import company.alex.com.parrotwings.data.remote.repositories.userInfo.UserRepository
import company.alex.com.parrotwings.domain.model.SearchUser
import javax.inject.Inject

class SearchUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    operator fun invoke(filter: String) = userRepository.searchUsers(filter)
        .map { dtos ->
            var users = mutableListOf<SearchUser>()
            dtos.forEach { users.add(SearchUser(it.id, it.name)) }

            return@map users
        }
}