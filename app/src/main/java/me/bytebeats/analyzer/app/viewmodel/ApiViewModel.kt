package me.bytebeats.analyzer.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import me.bytebeats.analyzer.app.intent.MainIntent
import me.bytebeats.analyzer.app.state.MainUiState
import me.bytebeats.analyzer.app.usercase.ApiUserCase

/**
 * Created by bytebeats on 2021/12/1 : 11:22
 * E-mail: happychinapc@gmail.com
 * Quote: Peasant. Educated. Worker
 */
class ApiViewModel(private val userCase: ApiUserCase) : ViewModel() {
    private val _apiIntent = Channel<MainIntent>(Channel.UNLIMITED)

    private val _state = MutableStateFlow<MainUiState>(MainUiState.Idle)

    val state: Flow<MainUiState>
        get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            _apiIntent.consumeAsFlow().collect {
                when (it) {
                    is MainIntent.GetColors -> getColors()
                }
            }
        }
    }

    fun sendIntent(intent: MainIntent) {
        viewModelScope.launch {
            _apiIntent.send(intent)
        }
    }

    private fun getColors() {
        viewModelScope.launch {
            _state.value = MainUiState.Loading
            _state.value = try {
                MainUiState.Colors(userCase.getKeys())
            } catch (e: Exception) {
                MainUiState.Error(e)
            }
        }
    }
}