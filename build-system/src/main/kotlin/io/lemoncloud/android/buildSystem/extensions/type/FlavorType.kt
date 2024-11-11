package io.lemoncloud.android.buildSystem.extensions.type

import io.lemoncloud.android.buildSystem.Config.VERSION_CODE
import io.lemoncloud.android.buildSystem.Config.VERSION_NAME

/**
 * [FlavorType]
 * @property name  A product flavor name
 * @property dimension A product flavor dimension
 * @property suffix An application id suffix; if null, It has no suffix
 * @property versionCode A version code
 * @property versionName An application version name (ex: 1.0.0, 1.0.1-beta, 2.0.3, ...)
 * @property buildConfigField A build config list that to read from build flavor. If you want to add build config, use it (ex, api url, key, ... ).
 * @property resourceValue A resource value list that to read from build flavor. If you want to add res value, use it (ex, app label).
 * @property manifestPlaceholder A manifestPlaceholder  that to read from build flavor.
 */
data class FlavorType(
    val name: String,
    val dimension: FlavorDimension = FlavorDimension.VERSION,
    val suffix: String? = null,
    val versionCode: Int = VERSION_CODE,
    val versionName: String = VERSION_NAME,
    val buildConfigField: List<BuildConfigField> = emptyList(),
    val resourceValue: List<ResourceType> = emptyList(),
    val manifestPlaceholder: Map<String, Any> = emptyMap()
) {

    // Change it, if you append or update dimension
    enum class FlavorDimension { VERSION }

}
