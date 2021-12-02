package com.example.lampa.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lampa.R
import com.example.lampa.databinding.ActivityMainBinding
import com.example.lampa.ui.adapter.ViewPagerAdapter
import com.example.lampa.ui.fragments.FavouritesFragment
import com.example.lampa.ui.fragments.StoriesFragment
import com.example.lampa.ui.fragments.VideoFragment
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        setupCustomToolBar()
        handleClickOnSearchButton()
        initViewPagerAdapter()
    }

    private fun initViewPagerAdapter() {
        val fragmentsList = listOf(StoriesFragment(), VideoFragment(), FavouritesFragment())
        val viewPagerAdapter = ViewPagerAdapter(fragments = fragmentsList, this)
        binding.pager.adapter = viewPagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = when (position) {
                0 -> resources.getString(R.string.stories)
                1 -> resources.getString(R.string.video)
                else -> resources.getString(R.string.favourites)
            }
        }.attach()
    }

    private fun setupCustomToolBar() {
        toolbar = binding.toolBar.customToolBar
        toolbar.inflateMenu(R.menu.main_menu)
        toolbar.title = resources.getString(R.string.toolbarTitle)
    }

    private fun handleClickOnSearchButton() {
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.search -> {
                    Toast.makeText(this, "Button is working", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }

}