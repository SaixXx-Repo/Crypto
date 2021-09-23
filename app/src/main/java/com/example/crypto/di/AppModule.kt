package com.example.crypto.di

import com.example.crypto.common.Constants.BASE_URL
import com.example.crypto.data.remote.CryptoApi
import com.example.crypto.data.repository.CoinRepositoryImpl
import com.example.crypto.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) //Live as long as the application does
object AppModule {

    @Provides
    @Singleton  // only single Instance
    fun provideCryptoApi(): CryptoApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CryptoApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CryptoApi): CoinRepository{
        return CoinRepositoryImpl(api)
    }

}