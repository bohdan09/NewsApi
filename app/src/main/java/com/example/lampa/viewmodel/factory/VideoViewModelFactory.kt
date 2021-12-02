package com.example.lampa.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lampa.viewmodel.VideoFragmentViewModel
import javax.inject.Inject
import javax.inject.Provider

class VideoViewModelFactory @Inject constructor(myViewModelProvider: Provider<VideoFragmentViewModel>) :
    ViewModelProvider.Factory {

    private val providers = mapOf<Class<*>, Provider<out ViewModel>>(
        VideoFragmentViewModel::class.java to myViewModelProvider
    )

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return providers[modelClass]!!.get() as T
    }
}