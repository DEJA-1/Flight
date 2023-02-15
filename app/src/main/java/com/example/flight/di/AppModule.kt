package com.example.flight.di

import com.example.flight.common.Constants
import com.example.flight.data.network.FlightApi
import com.example.flight.data.repository.FlightLocationRepositoryImpl
import com.example.flight.domain.repository.FlightLocationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFlightApi(): FlightApi {

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(FlightApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFlightLocationRepository(api: FlightApi) : FlightLocationRepository {
        return FlightLocationRepositoryImpl(api)
    }
}