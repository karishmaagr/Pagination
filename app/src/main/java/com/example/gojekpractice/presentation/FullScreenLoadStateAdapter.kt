package com.example.gojekpractice.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gojekpractice.R
import com.example.gojekpractice.databinding.ItemHeaderNetworkStateBinding
import com.google.android.material.button.MaterialButton

class FullScreenLoadStateAdapter<T : Any, VH : RecyclerView.ViewHolder>(
    private val adapter: PagingDataAdapter<T, VH>
) : LoadStateAdapter<FullScreenLoadStateAdapter.NetworkStateItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) =
        NetworkStateItemViewHolder(
            ItemHeaderNetworkStateBinding.bind(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_header_network_state, parent, false)
            )
        ) { adapter.retry() }

    override fun onBindViewHolder(holder: NetworkStateItemViewHolder, loadState: LoadState) =
        holder.bind(loadState)

    class NetworkStateItemViewHolder(
        private val binding: ItemHeaderNetworkStateBinding,
        private val retryCallback: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        var retryButton: MaterialButton =
            binding.parentView.findViewById<MaterialButton>(R.id.retry_button)

        init {

            retryButton.setOnClickListener { retryCallback() }
        }

        fun bind(loadState: LoadState) {
            with(binding) {
                binding.parentView.showLoader(loadState is LoadState.Loading)
                binding.parentView.showError(
                    loadState is LoadState.Error,
                    (loadState as? LoadState.Error)?.error?.message
                )
            }
        }
    }
}
