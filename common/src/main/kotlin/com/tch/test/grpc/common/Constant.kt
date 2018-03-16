package com.tch.test.grpc.common

import io.grpc.Context
import io.grpc.Metadata



/**
 * Created by tianch on 2018/3/15.
 */
class Constant {
    companion object {
        val SERVER_HOST = "localhost"
        val SERVER_PORT = 50051
        val CUSTOM_CLIENT_HEADER_KEY: Metadata.Key<String> = Metadata.Key.of("custom_client_header_key", Metadata.ASCII_STRING_MARSHALLER)
        val CUSTOM_SERVER_HEADER_KEY: Metadata.Key<String> = Metadata.Key.of("custom_server_header_key", Metadata.ASCII_STRING_MARSHALLER)
        val USER_ID_CTX_KEY = Context.key<String>("userId")
    }
}
