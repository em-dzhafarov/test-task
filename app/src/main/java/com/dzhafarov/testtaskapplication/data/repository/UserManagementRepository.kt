package com.dzhafarov.testtaskapplication.data.repository

import com.dzhafarov.testtaskapplication.data.datasource.UserManagementDataSource
import com.dzhafarov.testtaskapplication.data.model.Status
import com.dzhafarov.testtaskapplication.data.model.UserProfile
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserManagementRepository @Inject constructor(
    private val userManagementDataSource: UserManagementDataSource
) {

    suspend fun register(profile: UserProfile): Status<Unit> {
        return try {
            val result = userManagementDataSource.register(profile)
            Status.Success(result)
        } catch (error: Exception) {
            Status.Error(error)
        }
    }
}
