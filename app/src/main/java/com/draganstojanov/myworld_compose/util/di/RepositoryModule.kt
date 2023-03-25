package com.draganstojanov.myworld_compose.util.di

import com.draganstojanov.myworld_compose.repository.MyWorldRepository
import com.draganstojanov.myworld_compose.util.Prefs
import com.draganstojanov.myworld_compose.util.connectivity.ConnectivityState
import com.draganstojanov.myworld_compose.util.network.ResponseParser
import com.draganstojanov.myworld_compose.util.network.api.MyWorldApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMyWorldRepository(api: MyWorldApi, prefs: Prefs): MyWorldRepository = MyWorldRepository(api, prefs)

}
