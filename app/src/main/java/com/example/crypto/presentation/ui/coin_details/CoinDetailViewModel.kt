package com.example.crypto.presentation.ui.coin_details

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crypto.common.Constants.PARAM_COIN_ID
import com.example.crypto.common.Resource
import com.example.crypto.domain.use_case.get_coin.GetCoinUseCase
import com.example.crypto.presentation.ui.coin_list.CoinListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val coinDetail : MutableLiveData<CoinDetailState> = MutableLiveData()

    init {
        savedStateHandle.get<String>(PARAM_COIN_ID)?.let { coinId ->
            getCoinDetails(coinId)
        }
    }

    private fun getCoinDetails(coinId: String){
        getCoinUseCase(coinId).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    coinDetail.value = CoinDetailState(coin = result.data)
                }
                is Resource.Error -> {
                    coinDetail.value = CoinDetailState(error = result.message ?: "An unexpected error occured")
                }
                is Resource.Loading -> {
                    coinDetail.value = CoinDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)

    }

}