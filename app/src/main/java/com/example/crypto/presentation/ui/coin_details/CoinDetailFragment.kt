package com.example.crypto.presentation.ui.coin_details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.crypto.R
import com.example.crypto.databinding.CoinDetailFragmentBinding
import com.example.crypto.databinding.FragmentHomeBinding
import com.example.crypto.presentation.ui.coin_list.CoinListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinDetailFragment : Fragment() {

    private var _binding: CoinDetailFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CoinDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CoinDetailFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root


        viewModel.coinDetail.observe(viewLifecycleOwner, Observer {
            it.coin?.let { coinDetail ->
                binding.tvCoinDetailTitle.text = coinDetail.name
                binding.tvCoinDetailDescription.text = coinDetail.description
            }
        })

        return root

    }



}