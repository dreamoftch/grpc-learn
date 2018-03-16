package com.tch.test.grpc.server

import com.tch.test.grpc.common.Constant
import com.tch.test.grpc.helloworld.GreeterGrpc
import com.tch.test.grpc.helloworld.HelloReply
import com.tch.test.grpc.helloworld.HelloRequest
import com.tch.test.grpc.server.interceptor.MyHeaderServerInterceptor
import io.grpc.ServerBuilder
import io.grpc.ServerInterceptors
import io.grpc.stub.StreamObserver

/**
 * Created by tianch on 2018/3/15.
 */
fun main(args: Array<String>) {
    val server = ServerBuilder.forPort(Constant.SERVER_PORT)
            .addService(ServerInterceptors.intercept(GreeterImpl(), MyHeaderServerInterceptor()))
            .build()
    server.start()
    println("Server started, listening on " + Constant.SERVER_PORT)
    Runtime.getRuntime().addShutdownHook(object : Thread() {
        override fun run() {
            println("*** shutting down gRPC server since JVM is shutting down")
            server?.shutdown()
            println("*** server shut down")
        }
    })
    server.awaitTermination()
}

class GreeterImpl: GreeterGrpc.GreeterImplBase() {
    override fun sayHello(request: HelloRequest, responseObserver: StreamObserver<HelloReply>) {
        val name = request.name
        println("server receive req, name $name, header value: ${Constant.USER_ID_CTX_KEY.get()}")
        val reply = HelloReply.newBuilder().setMessage("hello $name").build()
        responseObserver.onNext(reply)
        responseObserver.onCompleted()
    }
}