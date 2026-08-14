package com.example.portfolio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PortfolioViewModel @Inject constructor(
    private val repository: PortfolioRepository
) : ViewModel() {

    // Converts the database Flow into a Compose-friendly StateFlow
    val investments: StateFlow<List<Investment>> = repository.allInvestments
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // A test function to add a hardcoded mutual fund to your database
    fun addDummyInvestment() {
        viewModelScope.launch {
            repository.addInvestment(
                Investment(
                    fundName = "Growth Equity Fund",
                    units = 150.5,
                    averageNav = 45.20,
                    currentNav = 50.10
                )
            )
        }
    }
}

