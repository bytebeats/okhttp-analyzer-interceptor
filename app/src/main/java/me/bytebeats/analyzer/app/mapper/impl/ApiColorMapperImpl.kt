package me.bytebeats.analyzer.app.mapper.impl

import me.bytebeats.analyzer.app.mapper.ApiColorMapper
import me.bytebeats.analyzer.app.util.convertARGB2Int

/**
 * Created by bytebeats on 2021/12/1 : 11:11
 * E-mail: happychinapc@gmail.com
 * Quote: Peasant. Educated. Worker
 */
class ApiColorMapperImpl : ApiColorMapper {
    override fun map(data: HashMap<String, IntArray>): Map<String, Int> =
        data.mapValues { entry -> convertARGB2Int(entry.value) }
}
