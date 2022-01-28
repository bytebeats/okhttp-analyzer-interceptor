package me.bytebeats.analyzer.transfer

import android.os.Bundle
import android.os.HandlerThread
import android.os.Process
import android.util.Log
import me.bytebeats.analyzer.MessageType
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
class LogDataTransfer : IDataTransfer {
    private lateinit var mLogHandler: LogDataHandler

    init {
        object : HandlerThread("OkHttpAnalyzor", Process.THREAD_PRIORITY_BACKGROUND) {
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
                    "${IDataTransfer.CONTENT_TYPE}${IDataTransfer.HEADER_DELIMITER}${IDataTransfer.SPACE}$type"
                )
            }
            val contentLength = it.contentLength()
            if (contentLength != -1L) {
                fastLog(
                    id,
                    MessageType.REQ_HEADER,
                    "${IDataTransfer.CONTENT_TYPE}${IDataTransfer.HEADER_DELIMITER}${IDataTransfer.SPACE}$contentLength"
                )
            }
        }
        val headers = request.headers
        for (name in headers.names()) {
            if (name.equals(
                    IDataTransfer.CONTENT_TYPE,
                    false
                ) || name.equals(IDataTransfer.CONTENT_LENGTH, false)
            ) {
                continue
            }
            fastLog(
                id,
                MessageType.REQ_HEADER,
                "$name${IDataTransfer.HEADER_DELIMITER}${headers[name]}"
            )
        }
        reqBody?.let {
            it.writeTo(buffer)
            largeLog(id, MessageType.REQ_BODY, buffer.readString(Charset.defaultCharset()))
        }
    }

    @Throws(IOException::class)
    override fun transferResponse(id: String, response: Response) {
        val respBodyCopy = response.peekBody(IDataTransfer.BODY_BUFFER_SIZE)
        largeLog(id, MessageType.RESP_BODY, respBodyCopy.string())

        val headers = response.headers
        logWithHandler(id, MessageType.RESP_STATUS, response.code.toString(), 0)
        for (name in headers.names()) {
            logWithHandler(
                id,
                MessageType.RESP_HEADER,
                "$name${IDataTransfer.HEADER_DELIMITER}${headers[name]}",
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
        data.putString(IDataTransfer.KEY_TAG, logTag(id, type))
        data.putString(IDataTransfer.KEY_VALUE, message)
        data.putInt(IDataTransfer.KEY_PARTS_COUNT, parts)
        msg.data = data
        mLogHandler.sendMessage(msg)
    }

    private fun largeLog(id: String, type: MessageType, content: String) {
        val length = content.length
        if (length > IDataTransfer.LOG_LENGTH) {
            val parts = length / IDataTransfer.LOG_LENGTH
            for (i in 0..parts) {
                val start = i * IDataTransfer.LOG_LENGTH
                var end = start + IDataTransfer.LOG_LENGTH
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
        return "${IDataTransfer.LOG_PREFIX}${IDataTransfer.DELIMITER}${id}${IDataTransfer.DELIMITER}${type.`val`}"
    }
}