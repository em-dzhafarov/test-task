package com.dzhafarov.testtaskapplication.di

import com.dzhafarov.testtaskapplication.data.datasource.LocalUserManagementDataSource
import com.dzhafarov.testtaskapplication.data.datasource.UserManagementDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindUserManagementDataSource(source: LocalUserManagementDataSource): UserManagementDataSource
}
