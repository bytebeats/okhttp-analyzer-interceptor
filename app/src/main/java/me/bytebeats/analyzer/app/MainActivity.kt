package me.bytebeats.analyzer.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import me.bytebeats.analyzer.app.intent.MainIntent
import me.bytebeats.analyzer.app.mapper.impl.ApiColorMapperImpl
import me.bytebeats.analyzer.app.repository.impl.ApiRepositoryImpl
import me.bytebeats.analyzer.app.retrofit.RetrofitService
import me.bytebeats.analyzer.app.source.ApiRemoteDataSource
import me.bytebeats.analyzer.app.state.MainUiState
import me.bytebeats.analyzer.app.ui.theme.OkHttpAnalyzorTheme
import me.bytebeats.analyzer.app.usercase.impl.ApiUserCaseImpl
import me.bytebeats.analyzer.app.viewmodel.ApiViewModel
import me.bytebeats.analyzer.app.viewmodel.factory.ApiViewModelFactory


class MainActivity : ComponentActivity() {

    private val apiViewModel by lazy {
        ViewModelProvider(
            this, ApiViewModelFactory(
                ApiUserCaseImpl(
                    ApiColorMapperImpl(), ApiRepositoryImpl(
                        ApiRemoteDataSource(RetrofitService.apiService)
                    )
                )
            )
        )[ApiViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OkHttpAnalyzorTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting(apiViewModel)
                }
            }
        }

    }
}

@Composable
fun Greeting(viewModel: ApiViewModel) {
    val uiState by remember(viewModel) {
        viewModel.state
    }.collectAsState(initial = MainUiState.Idle)

    val scrollState = rememberScrollState(0)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState, enabled = true),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Button(
            onClick = { viewModel.sendIntent(MainIntent.GetColors) },
            modifier = Modifier.padding(vertical = 10.dp)
        ) {
            Text(text = "Get Colors")
        }
        when (uiState) {
            is MainUiState.Idle -> {
                Text(text = "Idle")
            }
            is MainUiState.Loading -> {
                Text(text = "Loading")
            }
            is MainUiState.Colors -> {
                Text(
                    text = (uiState as MainUiState.Colors).data.keys.joinToString(
                        separator = ",",
                        prefix = "[",
                        postfix = "]"
                    )
                )
            }
            is MainUiState.Error -> {
                Text(text = (uiState as MainUiState.Error).t?.stackTraceToString().orEmpty())
            }
        }
    }
}