package com.example.crypto.presentation.ui.coin_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crypto.R
import com.example.crypto.databinding.FragmentHomeBinding
import com.example.crypto.presentation.ui.coin_list.adapters.CoinListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinListFragment : Fragment(R.layout.fragment_home) {


    private val viewModel: CoinListViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private lateinit var rvCoins: CoinListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupRecyclerView()


        viewModel.coins.observe(viewLifecycleOwner, Observer {
            rvCoins.coins = it.coins
        })




        return root
    }

    /**
     * Sets up the recyclerview
     */
    private fun setupRecyclerView() = binding.rvCoins.apply {
            rvCoins = CoinListAdapter(viewModel)
            adapter = rvCoins
            layoutManager = LinearLayoutManager(requireContext())
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}