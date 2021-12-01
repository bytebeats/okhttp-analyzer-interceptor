package me.bytebeats.analyzer.app.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.bytebeats.analyzer.app.usercase.ApiUserCase
import me.bytebeats.analyzer.app.viewmodel.ApiViewModel

/**
 * Created by bytebeats on 2021/12/1 : 11:27
 * E-mail: happychinapc@gmail.com
 * Quote: Peasant. Educated. Worker
 */
class ApiViewModelFactory(private val apiUserCase: ApiUserCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ApiViewModel::class.java)) {
            return ApiViewModel(apiUserCase) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}