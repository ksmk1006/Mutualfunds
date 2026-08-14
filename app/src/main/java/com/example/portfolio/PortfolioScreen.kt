package com.example.portfolio

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun PortfolioScreen(viewModel: PortfolioViewModel = hiltViewModel()) {
    // Reactively observe state changes from the ViewModel
    val investments by viewModel.investments.collectAsStateWithLifecycle()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.addDummyInvestment() }) {
                Text("+")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            items(investments) { fund ->
                InvestmentCard(fund)
            }
        }
    }
}

@Composable
fun InvestmentCard(investment: Investment) {
    // Basic math to calculate returns
    val totalValue = investment.units * investment.currentNav
    val totalInvested = investment.units * investment.averageNav
    val profit = totalValue - totalInvested

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = investment.fundName, 
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Current Value: $${String.format("%.2f", totalValue)}")
            Text(
                text = "Profit/Loss: $${String.format("%.2f", profit)}",
                color = if (profit >= 0) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
            )
        }
    }
}

