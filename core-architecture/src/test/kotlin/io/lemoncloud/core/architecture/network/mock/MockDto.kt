package io.lemoncloud.core.architecture.network.mock

import io.lemoncloud.core.architecture.network.BaseDto
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement

@Serializable
data class MockDto(
    val id: Long,
    val name: String
) : BaseDto<MockModel> {

    override fun toModel(): MockModel = MockModel(id, name)

    fun toJsonString(): String =
        Json.encodeToJsonElement(this).toString()
}
