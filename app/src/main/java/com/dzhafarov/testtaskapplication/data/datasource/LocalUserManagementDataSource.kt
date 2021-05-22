package com.dzhafarov.testtaskapplication.data.datasource

import com.dzhafarov.testtaskapplication.data.model.UserProfile
import kotlinx.coroutines.delay
import javax.inject.Inject

class LocalUserManagementDataSource @Inject constructor() : UserManagementDataSource {

    override suspend fun register(profile: UserProfile) {
        delay(1000) // emulation of long running operation
        // for a success result let's just return Unit
    }
}
