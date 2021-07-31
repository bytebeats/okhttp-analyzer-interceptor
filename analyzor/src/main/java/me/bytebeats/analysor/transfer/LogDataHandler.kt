package me.bytebeats.analysor.transfer

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log

/**
 * Created by bytebeats on 2021/7/31 : 21:08
 * E-mail: happychinapc@gmail.com
 * Quote: Peasant. Educated. Worker
 */
/**
 * A background Handler transferring okhttp data into verbose logs of Android platform.
 */
class LogDataHandler(looper: Looper) : Handler(looper) {
    override fun handleMessage(msg: Message) {
        val data = msg.data
        val parts = data.getInt(IDataTransfer.KEY_PARTS_COUNT, 0)
        if (parts > IDataTransfer.SLOW_DOWN_PARTS_AFTER) {
            try {
                Thread.sleep(5L)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
        val `val` = data.getString(IDataTransfer.KEY_VALUE)
        val tag = data.getString(IDataTransfer.KEY_TAG)
        if (!`val`.isNullOrEmpty() && !tag.isNullOrEmpty()) {
            Log.v(tag, `val`)
        }
    }
}