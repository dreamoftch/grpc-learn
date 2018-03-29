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

    private val logger = LoggerFactory.getLogger(SimpleRecoveryJob::class.java)

    override fun execute(context: JobExecutionContext) {
        val jobKey = context.getJobDetail().key

        logger.info("SimpleRecoveryJob: " + jobKey + " execute at " + Date().date2String())
    }

}