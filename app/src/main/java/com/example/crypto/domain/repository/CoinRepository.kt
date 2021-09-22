package com.example.crypto.domain.repository

import com.example.crypto.data.remote.dto.CoinDetailDto
import com.example.crypto.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto
}