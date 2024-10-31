package io.lemoncloud.core.util.extension

import kotlin.random.Random

object EnumExtensions {

    inline fun <reified T : Enum<T>> enumValueOfOrNull(value: String): T? =
        runCatching {
            enumValueOf<T>(value)
        }.getOrNull()

    inline fun <reified T : Enum<T>> find(predicate: (item: T) -> Boolean): T? = enumValues<T>().find { predicate(it) }

    inline fun <reified T : Enum<T>> random(random: Random = Random.Default): T =
        with(enumValues<T>()) { get(random.nextInt(size)) }
}
