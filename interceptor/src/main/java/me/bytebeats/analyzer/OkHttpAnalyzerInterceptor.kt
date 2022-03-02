package me.bytebeats.analyzer

import me.bytebeats.analyzer.transfer.BODY_BUFFER_SIZE
import me.bytebeats.analyzer.transfer.LogDataTransfer
import okhttp3.Interceptor
import okhttp3.Response
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.atomic.AtomicLong

/**
 * Created by bytebeats on 2021/7/31 : 20:37
 * E-mail: happychinapc@gmail.com
 * Quote: Peasant. Educated. Worker
 */
/**
 * An OkHttp Interceptor to intercept requests and responses
 */
class OkHttpAnalyzerInterceptor @JvmOverloads constructor(
    private val shouldIntercept: () -> Boolean = { true },
    private val maxBytesToIntercept: Long = BODY_BUFFER_SIZE
) : Interceptor {
    private val mDataTransfer by lazy { LogDataTransfer(maxBytesToIntercept) }
    private val mPreTime by lazy { AtomicLong() }
    private val mDateFormat by lazy { SimpleDateFormat("ddhhmmssSSS", Locale.US) }

    override fun intercept(chain: Interceptor.Chain): Response {
        val id = id()
        val startTime = System.currentTimeMillis()
        val request = chain.request()
        if (shouldIntercept()) {
            mDataTransfer.transferRequest(id, request)
        }
        val response = chain.proceed(request)
        if (shouldIntercept()) {
            try {
                mDataTransfer.transferResponse(id, response)
                mDataTransfer.transferDuration(id, System.currentTimeMillis() - startTime)
            } catch (e: Exception) {
                mDataTransfer.transferThrowable(id, e)
                mDataTransfer.transferDuration(id, System.currentTimeMillis() - startTime)
            }
        }
        return response
    }

    /**
     * Generates unique string id via a day and time
     * Based on a current time.
     * @return string id
     */
    @Synchronized
    private fun id(): String {
        var curTime = mDateFormat.format(Date()).toLong()
        var preTime = mPreTime.get()
        if (curTime <= preTime) {
            curTime = ++preTime
        }
        mPreTime.set(curTime)
        return curTime.toString(Character.MAX_RADIX)
    }
}