package me.bytebeats.analyzer.app.source

import me.bytebeats.analyzer.app.service.ApiService

/**
 * Created by bytebeats on 2021/12/1 : 11:01
 * E-mail: happychinapc@gmail.com
 * Quote: Peasant. Educated. Worker
 */
class ApiRemoteDataSource(private val apiService: ApiService) {
    suspend fun getColors(): HashMap<String, IntArray> = apiService.getColors()
}