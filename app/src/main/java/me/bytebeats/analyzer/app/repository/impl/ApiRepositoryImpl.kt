package me.bytebeats.analyzer.app.repository.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.bytebeats.analyzer.app.repository.ApiRepository
import me.bytebeats.analyzer.app.source.ApiRemoteDataSource

/**
 * Created by bytebeats on 2021/12/1 : 11:03
 * E-mail: happychinapc@gmail.com
 * Quote: Peasant. Educated. Worker
 */
class ApiRepositoryImpl(private val apiRemoteDataSource: ApiRemoteDataSource) : ApiRepository {
    override suspend fun getColors(): Map<String, IntArray> = withContext(Dispatchers.IO) {
        apiRemoteDataSource.getColors()
    }
}