package me.bytebeats.analyzer.app.util

import me.bytebeats.analyzer.app.ui.theme.Purple500

/**
 * Created by bytebeats on 2021/12/1 : 11:13
 * E-mail: happychinapc@gmail.com
 * Quote: Peasant. Educated. Worker
 */

fun convertARGB2Int(argb: LongArray): Long {
    if (argb.size == 3) {
        val r = (argb[0] shl 16) and 0x00FF0000
        val g = (argb[1] shl 8) and 0x0000FF00
        val b = argb[2] and 0x000000FF
        return (0xFF000000 or r or g or b)
    } else if (argb.size == 4) {
        val a = (argb[0] shl 24) and 0xFF000000
        val r = (argb[1] shl 16) and 0x00FF0000
        val g = (argb[2] shl 8) and 0x0000FF00
        val b = argb[3] and 0x000000FF
        return (a or r or g or b)
    }
    return Purple500.value.toLong()
}