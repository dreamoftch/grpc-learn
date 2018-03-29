package com.tch.test.grpc.quartz.job

import com.tch.test.grpc.quartz.util.date2String
import org.quartz.Job
import org.quartz.JobExecutionContext
import org.slf4j.LoggerFactory
import java.util.*

/**
 * Created by tianch on 2018/3/29.
 */
class SimpleRecoveryJob: Job {

    private val _log = LoggerFactory.getLogger(SimpleRecoveryJob::class.java)
    private val COUNT = "count"

    override fun execute(context: JobExecutionContext) {
        val jobKey = context.getJobDetail().key

        // if the job is recovering print a message
        if (context.isRecovering()) {
            _log.info("SimpleRecoveryJob: " + jobKey + " RECOVERING at " + Date().date2String())
        } else {
            _log.info("SimpleRecoveryJob: " + jobKey + " starting at " + Date().date2String())
        }

        val data = context.getJobDetail().jobDataMap
        var count: Int
        if (data.containsKey(COUNT)) {
            count = data.getInt(COUNT)
        } else {
            count = 0
        }
        count++
        data.put(COUNT, count)

        _log.info("SimpleRecoveryJob: " + jobKey + " done at " + Date().date2String() + " Execution #" + count)
    }

}