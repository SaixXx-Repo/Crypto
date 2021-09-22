package com.example.crypto.data.repository

import com.example.crypto.data.remote.CryptoApi
import com.example.crypto.data.remote.dto.CoinDetailDto
import com.example.crypto.data.remote.dto.CoinDto
import com.example.crypto.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CryptoApi
) : CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}