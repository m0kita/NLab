package com.example.smartlab.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.navArgs
import com.example.data.repository.AnalysesRepositoryImpl
import com.example.domain.model.Analyse
import com.example.domain.model.News
import com.example.domain.usecase.GetAnalysesUseCase
import com.example.domain.usecase.GetNewsUseCase
import com.example.smartlab.model.Profile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(): ViewModel() {
    var price = MutableStateFlow(0)
}