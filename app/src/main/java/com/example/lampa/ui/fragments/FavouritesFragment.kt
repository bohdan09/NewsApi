package com.example.lampa.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.filter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lampa.App
import com.example.lampa.databinding.FragmentFavouritesBinding
import com.example.lampa.ui.CustomItemDecorator
import com.example.lampa.ui.adapter.NewsLoadStateAdapter
import com.example.lampa.ui.adapter.RecyclerViewAdapter
import com.example.lampa.ui.adapter.TopRecyclerViewAdapter
import com.example.lampa.util.NewsType
import com.example.lampa.viewmodel.FavouritesFragmentViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavouritesFragment : Fragment() {
    private val model: FavouritesFragmentViewModel by activityViewModels {
        (activity?.application as App).getAppComponent().favouritesFragmentFactory()
    }

    @Inject
    lateinit var generalNewsAdapter: RecyclerViewAdapter

    @Inject
    lateinit var topNewsAdapter: TopRecyclerViewAdapter
    private lateinit var binding: FragmentFavouritesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity?.application as App).getAppComponent().inject(this)

        binding = FragmentFavouritesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupTopNewsRecycler()
        setupRecyclerView()
        newsRequest()
    }

    private fun newsRequest() {
        lifecycleScope.launch {
            model.news.collectLatest { news ->
                generalNewsAdapter.submitData(news.filter { it.type == NewsType.FAVOURITES.type })
            }
        }
        lifecycleScope.launch(Dispatchers.IO) {
            model.news.collectLatest { news ->
                topNewsAdapter.submitData(news.filter { it.top == "1" && it.type == NewsType.FAVOURITES.type })
            }
        }
    }

    private fun setupRecyclerView() {
        with(binding) {
            favouritesRecycler.layoutManager = LinearLayoutManager(requireContext())
            favouritesRecycler.adapter = generalNewsAdapter.withLoadStateHeaderAndFooter(
                header = NewsLoadStateAdapter { generalNewsAdapter.retry() },
                footer = NewsLoadStateAdapter { generalNewsAdapter.retry() }
            )
        }
    }

    private fun setupTopNewsRecycler() {
        with(binding) {
            favouritesTopNewsRecycler.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            favouritesTopNewsRecycler.adapter = topNewsAdapter
            val dividerItemDecoration =
                CustomItemDecorator(requireContext())
            favouritesTopNewsRecycler.addItemDecoration(dividerItemDecoration)
        }
    }
}