package me.bytebeats.analyzer.app.retrofit

import me.bytebeats.analyzer.OkHttpAnalyzerInterceptor
import me.bytebeats.analyzer.app.service.ApiService
import me.bytebeats.analyzer.app.util.JSON_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

/**
 * Created by bytebeats on 2021/12/1 : 10:45
 * E-mail: happychinapc@gmail.com
 * Quote: Peasant. Educated. Worker
 */
object RetrofitService {
    private fun createRetrofit(baseUrl: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(OkHttpAnalyzerInterceptor())
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .build()
            )
//            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    val apiService: ApiService = createRetrofit(JSON_URL).create(ApiService::class.java)
}