package com.example.lampa.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.lampa.domain.usecase.GetPagingSourceUseCase
import javax.inject.Inject

class FavouritesFragmentViewModel @Inject constructor(
    private val getPagingSourceUseCase: GetPagingSourceUseCase,
) : ViewModel() {

    val news = Pager(PagingConfig(pageSize = 5)) {
        getPagingSourceUseCase.execute()
    }.flow.cachedIn(viewModelScope)
}