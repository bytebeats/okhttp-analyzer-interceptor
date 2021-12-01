package me.bytebeats.analyzer.app.state

/**
 * Created by bytebeats on 2021/12/1 : 11:25
 * E-mail: happychinapc@gmail.com
 * Quote: Peasant. Educated. Worker
 */
sealed class MainUiState {
    object Idle : MainUiState()
    object Loading : MainUiState()
    data class Colors(val data: Map<String, Int>) : MainUiState()
    data class Error(val t: Throwable?) : MainUiState()
}
