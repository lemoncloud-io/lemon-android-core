package io.lemon.android.core.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import io.lemon.android.core.home.data.HomeEffect
import io.lemon.android.core.home.data.HomeEvent
import io.lemon.android.core.home.data.HomeState
import io.lemon.android.core.home.data.HomeViewModel


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel()
) {
    val state: HomeState by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.bindEffect(this) {
            when (it) {
                is HomeEffect.ShowSnackbar -> snackbarHostState.showSnackbar(it.message)
            }
        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(horizontal = 20.dp)
                .padding(it),
            verticalArrangement = Arrangement.spacedBy(48.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (state.isLoading) CircularProgressIndicator()
            else {
                Text(
                    text = "${state.count}",
                    fontSize = 36.sp
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        Button(
                            modifier = modifier.weight(1f),
                            onClick = { viewModel.onEvent(HomeEvent.OnClickCountUpButton) }) {
                            Text(text = "Count Up")
                        }
                        Button(
                            modifier = modifier.weight(1f),
                            onClick = { viewModel.onEvent(HomeEvent.OnClickCountDownButton) }) {
                            Text(text = "Count Down")
                        }
                    }
                }
            }
        }
    }
}


@Composable
@Preview
fun HomeScreenPreview(
    modifier: Modifier = Modifier,
) {
    HomeScreen()
}