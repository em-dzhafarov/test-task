package com.dzhafarov.testtaskapplication.data.datasource

import com.dzhafarov.testtaskapplication.data.model.UserProfile

interface UserManagementDataSource {
    suspend fun register(profile: UserProfile)
}