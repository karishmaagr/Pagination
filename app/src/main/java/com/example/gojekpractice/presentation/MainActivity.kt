package com.example.gojekpractice.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.gojekpractice.R
import com.example.gojekpractice.databinding.ActivityMainBinding
import com.example.gojekpractice.domain.StarWarsViewModel
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
        binding.rvStarWarPeople.adapter = starWarPeopleAdapter.withLoadStateHeaderAndFooter(
            header = PagingLoadStateAdapter(starWarPeopleAdapter),
            footer = PagingLoadStateAdapter(starWarPeopleAdapter)
        )

        setObserver()
    }

    private fun setObserver() {
        with(starWarPeopleAdapter) {

            lifecycleScope.launchWhenCreated {
                viewModel.starWarFlow.collectLatest { submitData(it) }
            }
        }
    }
}