package com.draganstojanov.myworld_compose.util.di

import android.content.Context
import com.draganstojanov.myworld_compose.util.Prefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PrefsModule {

    @Provides
    @Singleton
    fun providePrefs(@ApplicationContext context: Context): Prefs = Prefs(context)

}