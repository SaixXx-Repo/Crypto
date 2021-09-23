package com.example.crypto.presentation.ui.coin_list


import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crypto.common.Resource
import com.example.crypto.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

    val coins : MutableLiveData<CoinListState> = MutableLiveData()

    init {
        getCoins()
    }

    private fun getCoins(){
        getCoinsUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    coins.value = CoinListState(coins = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    coins.value = CoinListState(error = result.message ?: "An unexpected error occured")
                }
                is Resource.Loading -> {
                    coins.value = CoinListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}