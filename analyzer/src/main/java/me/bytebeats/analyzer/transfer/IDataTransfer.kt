package me.bytebeats.analyzer.transfer

import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import kotlin.jvm.Throws

/**
 * Created by bytebeats on 2021/7/31 : 20:52
 * E-mail: happychinapc@gmail.com
 * Quote: Peasant. Educated. Worker
 */
interface IDataTransfer {
    @Throws(IOException::class)
    fun transferRequest(id: String, request: Request)

    @Throws(IOException::class)
    fun transferResponse(id: String, response: Response)

    fun transferThrowable(id: String, throwable: Throwable)

    fun transferDuration(id: String, duration: Long)

    companion object {
        const val LOG_LENGTH = 4000
        internal const val SLOW_DOWN_PARTS_AFTER = 20
        internal const val BODY_BUFFER_SIZE = 1024L * 1024L * 10L
        internal const val LOG_PREFIX = "OkHttpAnalyzer"
        internal const val DELIMITER = "_"
        internal const val HEADER_DELIMITER = ':'
        internal const val SPACE = ' '
        internal const val KEY_TAG = "tag"
        internal const val KEY_VALUE = "value"
        internal const val KEY_PARTS_COUNT = "PARTS_COUNT"
        internal const val CONTENT_TYPE = "Content-Type"
        internal const val CONTENT_LENGTH = "Content-Length"
    }

}