package io.lemon.android.buildSystem.extensions.type


/**
 * [BuildConfigField]
 * @property type A resource type  (string,int,boolean)
 * @property name A resource name
 * @property value A resource value
 */
data class ResourceType(
    val type: Type,
    val name: String,
    val value: Any
) {
    enum class Type(val field: String) {
        StringType(field = "string"),
        IntType(field = "int"),
        BooleanType(field = "boolean")
    }

}