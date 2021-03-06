package me.bytebeats.analyzer.transfer

import android.os.Bundle
import android.os.HandlerThread
import android.os.Process
import android.util.Log
import me.bytebeats.analyzer.MessageType
import me.bytebeats.analyzer.copy
import okhttp3.Request
import okhttp3.Response
import okio.Buffer
import java.io.IOException
import java.nio.charset.Charset

/**
 * Created by bytebeats on 2021/7/31 : 21:06
 * E-mail: happychinapc@gmail.com
 * Quote: Peasant. Educated. Worker
 */

/**
 * Transfer okhttp data into verbose logs of Android platform
 */
class LogDataTransfer constructor(
    private val maxBytesToIntercept: Long
) : IDataTransfer {
    private lateinit var mLogHandler: LogDataHandler

    init {
        object : HandlerThread("OkHttpAnalyzer", Process.THREAD_PRIORITY_BACKGROUND) {
            override fun onLooperPrepared() {
                super.onLooperPrepared()
                mLogHandler = LogDataHandler(looper)
            }
        }.start()
    }


    @Throws(IOException::class)
    override fun transferRequest(id: String, request: Request) {
        fastLog(id, MessageType.REQ_METHOD, request.method)
        val url = request.url.toString()
        fastLog(id, MessageType.REQ_URL, url)
        fastLog(id, MessageType.REQ_TIME, System.currentTimeMillis().toString())
        val reqCopy = request.newBuilder().build()
        val buffer = Buffer()
        val reqBody = reqCopy.body
        reqBody?.let {
            val mediaType = it.contentType()
            mediaType?.let { type ->
                fastLog(
                    id,
                    MessageType.REQ_HEADER,
                    "${CONTENT_TYPE}${HEADER_DELIMITER}${SPACE}$type"
                )
            }
            val contentLength = it.contentLength()
            if (contentLength != -1L) {
                fastLog(
                    id,
                    MessageType.REQ_HEADER,
                    "${CONTENT_LENGTH}${HEADER_DELIMITER}${SPACE}$contentLength"
                )
            }
        }
        val headers = request.headers
        for (name in headers.names()) {
            if (name == CONTENT_TYPE || name == CONTENT_LENGTH) {
                continue
            }
            fastLog(
                id, MessageType.REQ_HEADER,
                "$name${HEADER_DELIMITER}${SPACE}${headers[name]}"
            )
        }
        reqBody?.let {
            val cl = it.contentLength()
            if (cl <= maxBytesToIntercept) {
                it.writeTo(buffer)
                largeLog(id, MessageType.REQ_BODY, buffer.readString(Charset.defaultCharset()))
            } else {
                fastLog(
                    id,
                    MessageType.REQ_BODY,
                    "The request body is hidden because content length($cl) exceeds maxBytesToIntercept($maxBytesToIntercept)"
                )
            }
        }
    }

    @Throws(IOException::class)
    override fun transferResponse(id: String, response: Response) {
        val respBodyCopy = response.copy()
        val cl = respBodyCopy.contentLength()
        if (cl <= maxBytesToIntercept) {
            largeLog(id, MessageType.RESP_BODY, respBodyCopy.string())
        } else {
            fastLog(
                id,
                MessageType.RESP_BODY,
                "The response body is hidden because content length($cl) exceeds maxBytesToIntercept($maxBytesToIntercept)"
            )
        }

        val headers = response.headers
        logWithHandler(id, MessageType.RESP_STATUS, response.code.toString(), 0)
        for (name in headers.names()) {
            logWithHandler(
                id,
                MessageType.RESP_HEADER,
                "$name${HEADER_DELIMITER}${headers[name]}",
                0
            )
        }
    }

    override fun transferThrowable(id: String, throwable: Throwable) {
        logWithHandler(id, MessageType.RESP_ERROR, throwable.localizedMessage, 0)
    }

    override fun transferDuration(id: String, duration: Long) {
        logWithHandler(id, MessageType.RESP_TIME, duration.toString(), 0)
        logWithHandler(id, MessageType.RESP_END, "--->", 0)
    }

    private fun fastLog(id: String, type: MessageType, message: String?) {
        if (!message.isNullOrEmpty()) {
            Log.v(logTag(id, type), message)
        }
    }

    private fun logWithHandler(id: String, type: MessageType, message: String?, parts: Int) {
        if (!::mLogHandler.isInitialized) return
        val msg = mLogHandler.obtainMessage()
        val data = Bundle()
        data.putString(KEY_TAG, logTag(id, type))
        data.putString(KEY_VALUE, message)
        data.putInt(KEY_PARTS_COUNT, parts)
        msg.data = data
        mLogHandler.sendMessage(msg)
    }

    private fun largeLog(id: String, type: MessageType, content: String) {
        val length = content.length
        if (length > LOG_LENGTH) {
            val parts = length / LOG_LENGTH
            for (i in 0..parts) {
                val start = i * LOG_LENGTH
                var end = start + LOG_LENGTH
                if (end > length) {
                    end = length
                }
                logWithHandler(id, type, content.substring(start, end), parts)
            }
        } else {
            logWithHandler(id, type, content, 0)
        }
    }

    private fun logTag(id: String, type: MessageType): String {
        return "${LOG_PREFIX}${DELIMITER}${id}${DELIMITER}${type.`val`}"
    }
}