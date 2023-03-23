package com.draganstojanov.myworld_compose.util.di

import android.content.Context
import com.draganstojanov.myworld_compose.util.BASE_URL
import com.draganstojanov.myworld_compose.util.VALUE_APPLICATION_JSON
import com.draganstojanov.myworld_compose.util.network.api.MyWorldApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideMyWorldApi(@ApplicationContext context: Context): MyWorldApi = retrofit(context).create(MyWorldApi::class.java)


    private fun getOkHttpClient(context: Context): OkHttpClient = OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()


    private val json = Json {
        ignoreUnknownKeys = true
    }

    @OptIn(ExperimentalSerializationApi::class)
    private fun retrofit(context: Context): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getOkHttpClient(context))
            .addConverterFactory(json.asConverterFactory(VALUE_APPLICATION_JSON.toMediaType()))
            .build()

}

