package me.bytebeats.analyzer.app.service

import retrofit2.http.GET

/**
 * Created by bytebeats on 2021/12/1 : 10:56
 * E-mail: happychinapc@gmail.com
 * Quote: Peasant. Educated. Worker
 */
interface ApiService {
    @GET("/itkacher/OkHttpProfiler/master/colors.json")
    suspend fun getColors(): Map<String, IntArray>
}