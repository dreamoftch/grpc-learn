package com.tch.test.grpc.server.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by tianch on 2018/3/21.
 */
fun format(date: Date): String {
    return SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)
}