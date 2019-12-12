package com.jonnydev.statistics.network

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("content-type","application/json")
                .addHeader("accept","application/json")
                .addHeader("Authorization","Bearer ${Tokens.AUTH}")
                .build()
        )
    }
}
