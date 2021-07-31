package me.bytebeats.analysor

/**
 * Created by bytebeats on 2021/7/31 : 20:42
 * E-mail: happychinapc@gmail.com
 * Quote: Peasant. Educated. Worker
 */
enum class MessageType(val `val`: String) {
    REQ_URL("RQU"),
    REQ_TIME("RQT"),
    REQ_METHOD("RQM"),
    REQ_HEADER("RQH"),
    REQ_BODY("RQB"),
    REQ_END("RQD"),
    RESP_TIME("RST"),
    RESP_STATUS("RSS"),
    RESP_HEADER("RSH"),
    RESP_BODY("RSB"),
    RESP_END("RSD"),
    RESP_ERROR("REE"),
    UNKNOWN("UNKNOWN");
}