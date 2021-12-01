package me.bytebeats.analyzer.app.repository

/**
 * Created by bytebeats on 2021/12/1 : 11:02
 * E-mail: happychinapc@gmail.com
 * Quote: Peasant. Educated. Worker
 */
interface ApiRepository {
    suspend fun getColors(): HashMap<String, IntArray>
}