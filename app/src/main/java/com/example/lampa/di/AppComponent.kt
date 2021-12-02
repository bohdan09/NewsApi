package com.example.lampa.di

import com.example.lampa.di.module.GeneralModule
import com.example.lampa.ui.fragments.FavouritesFragment
import com.example.lampa.ui.fragments.StoriesFragment
import com.example.lampa.ui.fragments.VideoFragment
import com.example.lampa.viewmodel.factory.FavouritesViewModelFactory
import com.example.lampa.viewmodel.factory.StoriesViewModelFactory
import com.example.lampa.viewmodel.factory.VideoViewModelFactory
import dagger.Component

@Component(modules = [GeneralModule::class])
interface AppComponent {
    fun inject(storiesFragment: StoriesFragment): StoriesFragment
    fun inject(videoFragment: VideoFragment): VideoFragment
    fun inject(favouritesFragment: FavouritesFragment): FavouritesFragment
    fun mainActivityFactory(): StoriesViewModelFactory
    fun videoFragmentFactory(): VideoViewModelFactory
    fun favouritesFragmentFactory(): FavouritesViewModelFactory
}