package com.tch.test.grpc.server.job;

import com.tch.test.grpc.server.util.DateUtilKt;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

public class MyJob implements org.quartz.Job {

      public MyJob() {
      }

      public void execute(JobExecutionContext context) throws JobExecutionException {
          System.err.println(DateUtilKt.format(new Date()) + ", Hello World!  MyJob is executing.");
      }
  }