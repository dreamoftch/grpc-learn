package com.tch.test.grpc.client

import com.tch.test.grpc.client.interceptor.MyHeaderClientInterceptor
import com.tch.test.grpc.common.Constant
import com.tch.test.grpc.helloworld.GreeterGrpc
import com.tch.test.grpc.helloworld.HelloRequest
import io.grpc.ClientInterceptors
import io.grpc.ManagedChannelBuilder
import java.util.concurrent.TimeUnit

/**
 * Created by tianch on 2018/3/15.
 */
fun main(args: Array<String>) {
    // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid needing certificates.
    val channel = ManagedChannelBuilder.forAddress(Constant.SERVER_HOST, Constant.SERVER_PORT)
            .usePlaintext(true)
            .build()
    val blockingStub = GreeterGrpc.newBlockingStub(ClientInterceptors.intercept(channel, MyHeaderClientInterceptor()))
    val resp = blockingStub.sayHello(HelloRequest.newBuilder().setName("tianch").build())
    println("resp: ${resp.message}")
    Runtime.getRuntime().addShutdownHook(object: Thread() {
        override fun run() {
            println("*** shutting down gRPC channel since JVM is shutting down")
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS)
            println("*** channel shut down")
        }
    })
}
