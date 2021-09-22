package com.example.crypto.domain.model

import com.example.crypto.data.remote.dto.*

data class CoinDetail(
    val description: String,
    val id: String,
    val name: String,
    val proofType: String,
    val rank: Int,
    val startedAt: String,
    val symbol: String,
    val tags: List<String>,
    val team: List<Team>,
)
