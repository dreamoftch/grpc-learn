package com.tch.test.grpc.quartz

import com.tch.test.grpc.quartz.job.SimpleRecoveryJob
import org.quartz.CronScheduleBuilder
import org.quartz.DateBuilder
import org.quartz.DateBuilder.futureDate
import org.quartz.JobBuilder.newJob
import org.quartz.TriggerBuilder
import org.quartz.impl.StdSchedulerFactory
import org.slf4j.LoggerFactory

/**
 * Created by tianch on 2018/3/29.
 */

private val _log = LoggerFactory.getLogger(GrpcQuartzTest::class.java)

class GrpcQuartzTest {}

fun main(args: Array<String>) {
    val sched = StdSchedulerFactory().scheduler

    sched.clear()

    _log.info("------- Scheduling Jobs ------------------")

    val job = newJob(SimpleRecoveryJob::class.java).withIdentity("my-job", "my-group") // put triggers in group
            // named after the cluster
            // node instance just to
            // distinguish (in logging)
            // what was scheduled from
            // where
            //.requestRecovery() // ask scheduler to re-execute this job if it was in progress when the scheduler went
            // down...
            .build()

    val trigger = TriggerBuilder.newTrigger().withIdentity("my-trigger", "my-group")
            .startAt(futureDate(1, DateBuilder.IntervalUnit.SECOND))
            .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")).build()

    sched.scheduleJob(job, trigger)

    _log.info("------- Starting Scheduler ---------------")
    sched.start()
    _log.info("------- Started Scheduler ----------------")
}