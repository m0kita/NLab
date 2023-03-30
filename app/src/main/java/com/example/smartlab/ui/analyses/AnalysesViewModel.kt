package com.example.smartlab.ui.analyses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.AnalysesRepositoryImpl
import com.example.domain.model.Analyse
import com.example.domain.model.News
import com.example.domain.usecase.GetAnalysesUseCase
import com.example.domain.usecase.GetNewsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnalysesViewModel: ViewModel() {
    private val repository = AnalysesRepositoryImpl()
    private val getAnalysesUseCase = GetAnalysesUseCase(repository)
    private val getNewsUseCase = GetNewsUseCase(repository)
    private val _news = MutableStateFlow(listOf<News>())
    val news get() = _news.asStateFlow()
    private val _analyses = MutableStateFlow(listOf<Analyse>())
    val analyses get() = _analyses.asStateFlow()
    val popular = mutableListOf<Analyse>()
    val covid = mutableListOf<Analyse>()
    val onkogenetik = mutableListOf<Analyse>()
    val zoj = mutableListOf<Analyse>()
    val trash = MutableStateFlow(mutableListOf<Analyse>())
    var size = MutableStateFlow(0)

    init {
        viewModelScope.launch {
            _news.value = getNewsUseCase.execute()
            _analyses.value = getAnalysesUseCase.execute()
        }
    }

    fun setupCategory() {
        analyses.value.map {
            when(it.category) {
                "Популярные" -> popular.add(it)
                "COVID" -> covid.add(it)
                else -> onkogenetik.add(it)
            }
        }
    }

}