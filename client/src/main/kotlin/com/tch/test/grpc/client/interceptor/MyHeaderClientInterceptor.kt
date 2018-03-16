package com.tch.test.grpc.client.interceptor

import com.tch.test.grpc.common.Constant
import io.grpc.*

/**
 * Created by tianch on 2018/3/15.
 */
class MyHeaderClientInterceptor: ClientInterceptor {

    override fun <ReqT : Any, RespT : Any> interceptCall(
            method: MethodDescriptor<ReqT, RespT>, 
            callOptions: CallOptions, 
            next: Channel): ClientCall<ReqT, RespT> {
        return object: ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(next.newCall(method, callOptions)) {
            override fun start(responseListener: Listener<RespT>, headers: Metadata) {
                println("put custom_client_header")
                headers.put(Constant.CUSTOM_CLIENT_HEADER_KEY, "customRequestValue")
                super.start(object: ForwardingClientCallListener.SimpleForwardingClientCallListener<RespT>(responseListener) {
                    override fun onHeaders(headers: Metadata) {
                        println("header received from server: $headers, server header: ${headers.get(Constant.CUSTOM_SERVER_HEADER_KEY)}")
                        super.onHeaders(headers)
                    }
                }, headers)
            }
        }
    }
    
}