package com.tch.test.grpc.server.interceptor

import com.tch.test.grpc.common.Constant
import io.grpc.*

/**
 * Created by tianch on 2018/3/15.
 */
class MyHeaderServerInterceptor : ServerInterceptor {

    override fun <ReqT : Any, RespT : Any> interceptCall(
            call: ServerCall<ReqT, RespT>,
            requestHeaders: Metadata,
            next: ServerCallHandler<ReqT, RespT>): ServerCall.Listener<ReqT> {
        val clientHeaderValue = requestHeaders.get(Constant.CUSTOM_CLIENT_HEADER_KEY)
        println("header received from client: $requestHeaders, client-header: $clientHeaderValue")
        //将自定义的header信息放到上下文中(注意context是不可变的,调用withValue之后返回的新的context)
        //为了在后面的上下文中使用到自定义的数据，需要使用新的context:
        //参考(https://github.com/saturnism/grpc-java-by-example/blob/master/metadata-context-example/src/main/java/com/example/grpc/server/JwtServerInterceptor.java)
        val ctx = Context.current().withValue(Constant.USER_ID_CTX_KEY, clientHeaderValue)
        return Contexts.interceptCall(ctx, call, requestHeaders, next);
    }
}