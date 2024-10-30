package io.lemoncloud.core.architecture.network.mock

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HEAD

interface MockService {

    @HEAD("mock")
    suspend fun head(): Response<Void>

    @GET("mock")
    suspend fun get(): Response<MockDto>
}
