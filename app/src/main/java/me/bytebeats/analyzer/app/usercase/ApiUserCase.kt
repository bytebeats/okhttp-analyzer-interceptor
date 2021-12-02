package me.bytebeats.analyzer.app.usercase

/**
 * Created by bytebeats on 2021/12/1 : 11:06
 * E-mail: happychinapc@gmail.com
 * Quote: Peasant. Educated. Worker
 */
interface ApiUserCase {
    suspend fun getKeys(): Map<String, Long>
}