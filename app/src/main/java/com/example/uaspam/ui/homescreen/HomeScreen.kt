package com.example.uaspam.ui.homescreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uaspam.model.Aplikasi
import com.example.uaspam.navigation.DestinasiNavigasi
import com.example.uaspam.ui.LanggananTopAppBar
import com.example.uaspam.ui.PenyediaViewModel

object DestinasiHome : DestinasiNavigasi {
    override val route = "home"
    override val titleRes = "Subscription Apps"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    viewModel: HomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LanggananTopAppBar(
                title = DestinasiHome.titleRes,
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = ""
                )
            }
        },
    ) { innerPadding ->
        val uiStateSiswa by viewModel.homeUIState.collectAsState()
        BodyHome(
            itemApp = uiStateSiswa.listApp,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            onSiswaClick = onDetailClick
        )
    }
}

@Composable
fun BodyHome(
    itemApp: List<Aplikasi>,
    modifier: Modifier = Modifier,
    onSiswaClick: (String) -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if (itemApp.isEmpty()) {
            Text(
                text = "Tidak ada data Aplikasi",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        } else {
            ListApp(
                itemApp = itemApp,
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                onItemClick = { onSiswaClick(it.id) }
            )
        }
    }
}

@Composable
fun ListApp(
    itemApp: List<Aplikasi>,
    modifier: Modifier = Modifier,
    onItemClick: (Aplikasi) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        this.items(itemApp, key = { it.id }) { aplikasi ->
            DataApp(
                aplikasi = aplikasi,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(aplikasi) }
            )
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun DataApp(
    aplikasi: Aplikasi,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = aplikasi.nama,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = aplikasi.harga,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = aplikasi.ketr,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}