package io.lemoncloud.core.architecture.network

import io.lemoncloud.core.architecture.network.JsonConverterFactory.Companion.Default
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit

class TestRetrofit(
    private val mockWebServer: MockWebServer
) {
    fun build(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(Default)
            .build()
    }
}
