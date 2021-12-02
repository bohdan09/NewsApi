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
import com.example.lampa.databinding.FragmentStoriesBinding
import com.example.lampa.ui.CustomItemDecorator
import com.example.lampa.ui.adapter.NewsLoadStateAdapter
import com.example.lampa.ui.adapter.RecyclerViewAdapter
import com.example.lampa.ui.adapter.TopRecyclerViewAdapter
import com.example.lampa.util.NewsType
import com.example.lampa.viewmodel.StoriesFragmentViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


class StoriesFragment : Fragment() {
    private val model: StoriesFragmentViewModel by activityViewModels {
        (activity?.application as App).getAppComponent().mainActivityFactory()
    }

    @Inject
    lateinit var generalNewsAdapter: RecyclerViewAdapter

    @Inject
    lateinit var topNewsAdapter: TopRecyclerViewAdapter
    private lateinit var binding: FragmentStoriesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity?.application as App).getAppComponent().inject(this)
        binding = FragmentStoriesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupTopNewsRecycler()
        setupRecyclerView()
        newsRequest()
    }

    private fun newsRequest() {
        lifecycleScope.launch(Dispatchers.IO) {
            model.news.collectLatest { news ->
                generalNewsAdapter.submitData(news.filter { it.type == NewsType.STORIES.type })
            }
        }

        lifecycleScope.launch(Dispatchers.IO) {
            model.news.collectLatest { news ->
                topNewsAdapter.submitData(news.filter { it.top == "1" })
            }
        }
    }

    private fun setupRecyclerView() {
        with(binding) {
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = generalNewsAdapter.withLoadStateHeaderAndFooter(
                header = NewsLoadStateAdapter { generalNewsAdapter.retry() },
                footer = NewsLoadStateAdapter { generalNewsAdapter.retry() }
            )
        }
    }

    private fun setupTopNewsRecycler() {
        binding.topNewsItem.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.topNewsItem.adapter = topNewsAdapter
        val dividerItemDecoration =
            CustomItemDecorator(requireContext())
        binding.topNewsItem.addItemDecoration(dividerItemDecoration)
    }

}