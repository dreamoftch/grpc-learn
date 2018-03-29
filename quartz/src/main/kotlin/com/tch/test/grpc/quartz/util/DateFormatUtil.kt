package com.tch.test.grpc.quartz.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by tianch on 2018/3/29.
 */
fun Date.date2String(): String {
    val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    return format.format(this)
}