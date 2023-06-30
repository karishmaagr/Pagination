package com.example.gojekpractice.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gojekpractice.R
import com.example.gojekpractice.databinding.ActivityMainBinding
import com.example.gojekpractice.domain.StarWarsViewModel
import com.example.gojekpractice.model.StarWarsPeopleData
import kotlinx.coroutines.flow.collectLatest

//https://harunwangereka.medium.com/android-paging-library-with-kotlin-coroutines-b96602e3fae3
//https://proandroiddev.com/paging-3-easier-way-to-pagination-part-1-584cad1f4f61 -- Pagination 3
//https://harunwangereka.medium.com/android-paging-library-with-kotlin-coroutines-b96602e3fae3 - Pagination
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var starWarPeopleAdapter: StarWarPeopleAdapter

    private val viewModel: StarWarsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        starWarPeopleAdapter = StarWarPeopleAdapter(this)
        binding.rvStarWarPeople.adapter = starWarPeopleAdapter.withLoadStateAdapters(
            header = PagingLoadStateAdapter(starWarPeopleAdapter),
            footer = PagingLoadStateAdapter(starWarPeopleAdapter)
        )

        setObserver()
    }

    private fun setObserver() {
        with(starWarPeopleAdapter) {
            binding.swipeRefresh.setOnRefreshListener { refresh() }

            lifecycleScope.launchWhenCreated {
                viewModel.starWarFlow.collectLatest {
                    submitData(it)
                }
            }

            viewModel.errorMessage.observe(this@MainActivity, Observer {
                Toast.makeText(this@MainActivity, it, Toast.LENGTH_LONG).show()
            })

            lifecycleScope.launchWhenCreated {
                loadStateFlow.collectLatest {
                    binding.swipeRefresh.isRefreshing = it.refresh is LoadState.Loading
                }
            }
        }
    }

    fun <T : Any, V : RecyclerView.ViewHolder> PagingDataAdapter<T, V>.withLoadStateAdapters(
        header: PagingLoadStateAdapter<StarWarsPeopleData, StarWarPeopleAdapter.MyViewHolder> = PagingLoadStateAdapter(starWarPeopleAdapter),
        footer: PagingLoadStateAdapter<StarWarsPeopleData, StarWarPeopleAdapter.MyViewHolder> = PagingLoadStateAdapter(starWarPeopleAdapter)
    ): ConcatAdapter {
        addLoadStateListener { loadStates ->
            header.loadState = loadStates.refresh
            footer.loadState = loadStates.append
        }

        return ConcatAdapter(header, this, footer)
    }
}