package me.bytebeats.analyzer.app.mapper

/**
 * Created by bytebeats on 2021/12/1 : 11:10
 * E-mail: happychinapc@gmail.com
 * Quote: Peasant. Educated. Worker
 */
interface ApiColorMapper {
    fun map(data: HashMap<String, IntArray>): Map<String, Int>
}