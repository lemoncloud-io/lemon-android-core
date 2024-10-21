package io.lemoncloud.core.architecture.network

/**
 * 네트워크와 통신하기 위해 사용되는 DTO를 구조화 합니다.
 * `BaseDTO`를 사용하기 위해서는 도메인 모델 객체를 별도로 정의해야만 합니다.
 *
 * ```
 * data class MockModel(
 *     val id: Long,
 *     val name: String
 * )
 *
 * data class MockDto(
 *     val id: Long,
 *     val name: String
 * ) : BaseDto<MockModel> {
 *
 *     constructor(model: MockModel) : this(model.id, model.name)
 *
 *     override fun toModel(): MockModel = MockModel(id, name)
 * }
 *```
 */
interface BaseDto<out Model> {

    /**
     * DTO를 Model 객체로 반환합니다.
     */
    fun toModel(): Model
}
