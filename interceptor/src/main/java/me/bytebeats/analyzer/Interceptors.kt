package me.bytebeats.analyzer

import okhttp3.Response
import okhttp3.ResponseBody

/**
 * Created by bytebeats on 2022/3/2 : 16:31
 * E-mail: happychinapc@gmail.com
 * Quote: Peasant. Educated. Worker
 */

fun Response.copy(): ResponseBody {
    val body = this.body()!!
    val buffer = body.source().buffer.clone()
    return ResponseBody.create(body.contentType(), body.contentLength(), buffer)
}