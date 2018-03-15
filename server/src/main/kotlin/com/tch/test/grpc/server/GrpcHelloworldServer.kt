package com.tch.test.grpc.server

import com.tch.test.grpc.common.Environment
import com.tch.test.grpc.server.helloworld.GreeterGrpc
import com.tch.test.grpc.server.helloworld.HelloReply
import com.tch.test.grpc.server.helloworld.HelloRequest
import io.grpc.ServerBuilder
import io.grpc.stub.StreamObserver

/**
 * Created by tianch on 2018/3/15.
 */
fun main(args: Array<String>) {
    val server = ServerBuilder.forPort(Environment.SERVER_PORT).addService(GreeterImpl()).build()
    server.start()
    println("Server started, listening on " + Environment.SERVER_PORT)
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
        println("server receive req, name $name")
        val reply = HelloReply.newBuilder().setMessage("hello $name").build()
        responseObserver.onNext(reply)
        responseObserver.onCompleted()
    }
}