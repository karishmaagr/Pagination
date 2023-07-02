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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

//https://harunwangereka.medium.com/android-paging-library-with-kotlin-coroutines-b96602e3fae3
//https://proandroiddev.com/paging-3-easier-way-to-pagination-part-1-584cad1f4f61 -- Pagination 3
//https://harunwangereka.medium.com/android-paging-library-with-kotlin-coroutines-b96602e3fae3 - Pagination
//https://github.com/metinozcura/RickAndMorty
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var starWarPeopleAdapter: StarWarPeopleAdapter

    private val viewModel: StarWarsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        starWarPeopleAdapter = StarWarPeopleAdapter(this)
        binding.rvStarWarPeople.adapter = starWarPeopleAdapter.withLoadStateAdapters(
            header = PagingHeaderLoadStateAdapter(starWarPeopleAdapter),
            footer = PagingLoadStateAdapter(starWarPeopleAdapter)
        )

        setObserver()
    }

    private fun setObserver() {
        with(starWarPeopleAdapter) {

            lifecycleScope.launchWhenCreated {
                viewModel.starWarFlow.collectLatest {
                    submitData(it)
                }
            }

        }
    }

    fun <T : Any, V : RecyclerView.ViewHolder> PagingDataAdapter<T, V>.withLoadStateAdapters(
        header: PagingHeaderLoadStateAdapter<StarWarsPeopleData, StarWarPeopleAdapter.MyViewHolder> = PagingHeaderLoadStateAdapter(
            starWarPeopleAdapter
        ),
        footer: PagingLoadStateAdapter<StarWarsPeopleData, StarWarPeopleAdapter.MyViewHolder> = PagingLoadStateAdapter(
            starWarPeopleAdapter
        )
    ): ConcatAdapter {
        addLoadStateListener { loadStates ->
            header.loadState = loadStates.refresh
            footer.loadState = loadStates.append
        }

        return ConcatAdapter(header, this, footer)
    }
}