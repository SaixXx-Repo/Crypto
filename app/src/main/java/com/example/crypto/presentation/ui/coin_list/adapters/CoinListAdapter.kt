package com.example.crypto.presentation.ui.coin_list.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.crypto.R
import com.example.crypto.databinding.ItemCoinBinding
import com.example.crypto.domain.model.Coin
import com.example.crypto.presentation.ui.coin_list.CoinListViewModel

class CoinListAdapter(val coinListViewModel: CoinListViewModel) : RecyclerView.Adapter<CoinListAdapter.CoinViewHolder>() {

    inner class CoinViewHolder(val binding: ItemCoinBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Coin>(){
        override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
    var coins: List<Coin>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val binding = ItemCoinBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return CoinViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {

        val coin = coins[position]

        holder.binding.apply {

            tvCoinName.text = coin.name
            tvCoinRank.text = coin.rank.toString() + "."
            tvCoinSymbol.text = coin.symbol
        }

        holder.itemView.setOnClickListener {
            var navController: NavController
            navController = Navigation.findNavController(holder.itemView)
            val bundle = bundleOf("coinId" to coin.id)
            navController.navigate(R.id.action_navigation_home_to_coinDetailFragment,bundle)
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}