package io.lemoncloud.core.util.extension

import kotlin.random.Random

object EnumExtensions {

    /**
     * enum 이름을 통해 enum 값을 불러옵니다.
     * @param value enum 이름
     */
    inline fun <reified T : Enum<T>> enumValueOfOrNull(value: String): T? =
        runCatching {
            enumValueOf<T>(value)
        }.getOrNull()

    /**
     * enum 목록에서 특정 조건에 일치하는 enum 값을 찾습니다.
     * @param predicate enum 을 찾기 위한 조건식
     */
    inline fun <reified T : Enum<T>> find(predicate: (item: T) -> Boolean): T? = enumValues<T>().find { predicate(it) }

    /**
     * 무작위 enum 값을 반환합니다.
     * @param random 랜덤 방식; 기본값은 [Random.Default]
     */
    inline fun <reified T : Enum<T>> random(random: Random = Random.Default): T =
        with(enumValues<T>()) { get(random.nextInt(size)) }

}
