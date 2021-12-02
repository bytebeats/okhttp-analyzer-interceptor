package me.bytebeats.analyzer.app.mapper.impl

import me.bytebeats.analyzer.app.mapper.ApiColorMapper
import me.bytebeats.analyzer.app.util.convertARGB2Int

/**
 * Created by bytebeats on 2021/12/1 : 11:11
 * E-mail: happychinapc@gmail.com
 * Quote: Peasant. Educated. Worker
 */
class ApiColorMapperImpl : ApiColorMapper {
    override fun map(data: Map<String, LongArray>): Map<String, Long> =
        data.mapValues { entry -> convertARGB2Int(entry.value) }
}
