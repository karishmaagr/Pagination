package com.example.gojekpractice.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gojekpractice.R
import com.example.gojekpractice.databinding.ItemHeaderNetworkStateBinding
import com.example.gojekpractice.databinding.ItemNetworkStateHeaderBinding

class PagingHeaderLoadStateAdapter<T : Any, VH : RecyclerView.ViewHolder>(
    private val adapter: PagingDataAdapter<T, VH>
) : LoadStateAdapter<PagingHeaderLoadStateAdapter.NetworkStateItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) =
        NetworkStateItemViewHolder(
            ItemNetworkStateHeaderBinding.bind(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_network_state_header, parent, false)
            )
        ) { adapter.retry() }

    override fun onBindViewHolder(holder: NetworkStateItemViewHolder, loadState: LoadState) =
        holder.bind(loadState)

    class NetworkStateItemViewHolder(
        private val binding: ItemNetworkStateHeaderBinding,
        private val retryCallback: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.retryButton.setOnClickListener { retryCallback() }
        }

        fun bind(loadState: LoadState) {
            with(binding) {
                progressBar.isVisible = loadState is LoadState.Loading
                retryButton.isVisible = loadState is LoadState.Error
                errorMsg.isVisible =
                    !(loadState as? LoadState.Error)?.error?.message.isNullOrBlank()
                errorMsg.text = (loadState as? LoadState.Error)?.error?.message
            }
        }
    }
}
