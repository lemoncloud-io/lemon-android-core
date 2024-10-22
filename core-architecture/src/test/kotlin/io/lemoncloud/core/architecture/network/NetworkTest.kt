package io.lemoncloud.core.architecture.network

import com.google.common.truth.Truth
import io.lemoncloud.core.architecture.domain.DataState
import io.lemoncloud.core.architecture.network.HttpResponse.Companion.toDataState
import io.lemoncloud.core.architecture.network.mock.MockService
import io.lemoncloud.core.architecture.network.mock.MockDto
import io.lemoncloud.core.architecture.network.mock.MockModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

class NetworkTest {

    private lateinit var testRetrofit: TestRetrofit
    private lateinit var mockWebServer: MockWebServer

    private val testDto = MockDto(id = 1238, name = "lemon")
    private val testModel = MockModel(id = 1238, name = "lemon")
    private val testJsonString = """{"id":1238,"name":"lemon"}""".trimIndent()

    @Before
    fun setUp() {
        mockWebServer = MockWebServer().apply {
            start()
        }
        testRetrofit = TestRetrofit(mockWebServer)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    /**
     * dto를 json 문자열로 파싱했을 때 성공적으로 변환되는지 확인합니다.
     */
    @Test
    fun toJsonString_serializableDto_returnsExpectedString() {
        val dto = testDto

        val jsonString = dto.toJsonString()

        Truth.assertThat(jsonString).isEqualTo(testJsonString)
    }

    /**
     * 서버로 부터 object를 GET 요청 했을 때, 적절한 응답을 반환하는지 확인합니다.
     */
    @Test
    fun getObject_withValidRequestGet_returnsExpectedObject() = runTest {
        val jsonString = testDto.toJsonString()
        val testService = testRetrofit.build().create(MockService::class.java)
        mockWebServer.enqueue(MockResponse().setBody(jsonString).setResponseCode(200))

        val response = testService.get()

        Truth.assertThat(response.body()).isEqualTo(testDto)
    }

    /**
     *  서버로 부터 반환 값이 없는 Head 요청을 했을 때, `createEmptyHttpResponse()`가 적절한 응답을 반환하는지 확인합니다.
     */
    @Test
    fun createEmptyHttpResponse_withValidRequestHead_returnsSuccess() = runTest {
        val testService = testRetrofit.build().create(MockService::class.java)
        mockWebServer.enqueue(MockResponse().setResponseCode(200))

        val networkResponse = testService.head()
        val httpResponse = HttpResponse.createEmpty { networkResponse }.first()

        Truth.assertThat(networkResponse.code()).isEqualTo(200)
        Truth.assertThat(httpResponse).isInstanceOf(HttpResponse.Success::class.java)
        Truth.assertThat((httpResponse as HttpResponse.Success).data).isEqualTo(Unit)
    }

    /**
     *  서버로 부터 GET 요청을 했을 때, `createHttpResponse()`가 적절한 응답을 반환하는지 확인합니다.
     */
    @Test
    fun createHttpResponse_withValidRequestGet_returnsSuccess() = runTest {
        val jsonString = testDto.toJsonString()
        val testService = testRetrofit.build().create(MockService::class.java)
        mockWebServer.enqueue(MockResponse().setBody(jsonString).setResponseCode(200))

        val networkResponse = testService.get()
        val httpResponse = HttpResponse.create { networkResponse }.first()

        Truth.assertThat(networkResponse.code()).isEqualTo(200)
        Truth.assertThat(httpResponse).isInstanceOf(HttpResponse.Success::class.java)
        Truth.assertThat((httpResponse as HttpResponse.Success).data).isEqualTo(testDto)
    }

    /**
     *  서버로 부터 올바르지 않은 GET 요청을 했을 때, `createHttpResponse()`가 적절한 응답을 반환하는지 확인합니다.
     */
    @Test
    fun createHttpResponse_withInvalidRequestGet_returnsFail() = runTest {
        val jsonString = testDto.toJsonString()
        val testService = testRetrofit.build().create(MockService::class.java)
        mockWebServer.enqueue(MockResponse().setBody(jsonString).setResponseCode(400))

        val networkResponse = testService.get()
        val httpResponse = HttpResponse.create { networkResponse }.first()

        Truth.assertThat(networkResponse.code()).isEqualTo(400)
        Truth.assertThat(httpResponse).isInstanceOf(HttpResponse.Fail::class.java)
        Truth.assertThat((httpResponse as HttpResponse.Fail).code).isEqualTo(400)
    }

    /**
     *  Success [HttpResponse]를 [toDataState] 를 통해 변환하였을 때, 올바른 값을 가져오는지 확인합니다.
     */
    @Test
    fun toDataState_withSuccessHttpResponse_returnsSuccessModel() = runTest {
        val httpResponse = HttpResponse.Success(data = testDto)

        val dataState = httpResponse.toDataState()

        Truth.assertThat(dataState).isInstanceOf(DataState.Success::class.java)
        Truth.assertThat((dataState as DataState.Success).data).isEqualTo(testModel)
    }

    /**
     *  Fail [HttpResponse]를 [toDataState] 를 통해 변환하였을 때, 올바른 값을 가져오는지 확인합니다.
     */
    @Test
    fun toDataState_withFailHttpResponse_returnsFail() = runTest {
        val httpResponse = HttpResponse.Fail<MockDto>()

        val dataState = httpResponse.toDataState()

        Truth.assertThat(dataState).isInstanceOf(DataState.Fail::class.java)
        Truth.assertThat((dataState as DataState.Fail).exceptions).isEqualTo(null)
    }

    /**
     *  Success [HttpResponse]를 [toDataState]와 매퍼를 통해 변환 하였을 때, 올바른 모델 값을 가져오는지 확인합니다.
     */
    @Test
    fun toDataState_withSuccessHttpResponseAndMapper_returnsSuccessModel() = runTest {
        val httpResponse = HttpResponse.Success(data = testDto)

        val dataState = httpResponse.toDataState { Unit }

        Truth.assertThat(dataState).isInstanceOf(DataState.Success::class.java)
        Truth.assertThat((dataState as DataState.Success).data).isEqualTo(Unit)
    }
}
