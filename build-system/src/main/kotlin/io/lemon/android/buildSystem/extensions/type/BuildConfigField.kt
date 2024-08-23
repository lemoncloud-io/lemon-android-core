package io.lemon.android.buildSystem.extensions.type

/**
 * [BuildConfigField]
 * @property type A build config type (string,int,boolean)
 * @property name A build config name
 * @property value A build config value (If you use "", parse to \"\")
 */
data class BuildConfigField(
    val type: Type,
    val name: String,
    val value: Any
) {
    enum class Type(val field: String) {
        StringType(field = "String"),
        IntType(field = "int"),
        BooleanType(field = "boolean")
    }
}
