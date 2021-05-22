package com.dzhafarov.testtaskapplication.domain

import com.dzhafarov.testtaskapplication.data.model.Status
import com.dzhafarov.testtaskapplication.data.model.UserProfile
import com.dzhafarov.testtaskapplication.data.repository.UserManagementRepository
import javax.inject.Inject

class UserRegistrationUseCase @Inject constructor(
    private val userManagementRepository: UserManagementRepository
) {

    suspend fun create(userName: String, email: String): Status<Unit> {
        val profile = UserProfile(userName, email)
        return userManagementRepository.register(profile)
    }
}
