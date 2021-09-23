package com.example.crypto.presentation.ui.coin_details

import com.example.crypto.domain.model.Coin
import com.example.crypto.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)
