package me.bytebeats.analyzer.app.usercase.impl

import me.bytebeats.analyzer.app.mapper.ApiColorMapper
import me.bytebeats.analyzer.app.repository.ApiRepository
import me.bytebeats.analyzer.app.usercase.ApiUserCase

/**
 * Created by bytebeats on 2021/12/1 : 11:08
 * E-mail: happychinapc@gmail.com
 * Quote: Peasant. Educated. Worker
 */
class ApiUserCaseImpl(
    private val mapper: ApiColorMapper,
    private val apiRepository: ApiRepository
) : ApiUserCase {
    override suspend fun getKeys(): Map<String, Int> = mapper.map(apiRepository.getColors())
}