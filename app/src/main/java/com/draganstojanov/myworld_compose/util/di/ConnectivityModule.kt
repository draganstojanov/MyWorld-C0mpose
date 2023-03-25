package com.draganstojanov.myworld_compose.util.di

import android.content.Context
import com.draganstojanov.myworld_compose.util.connectivity.ConnectivityState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ConnectivityModule {

    @Provides
    @Singleton
    fun provideConnectivityState(@ApplicationContext context: Context): ConnectivityState = ConnectivityState(context)

}