package io.lemoncloud.core.android.common.model

enum class MIMEType(val type: String) {
    ALL(type = "*/*"),
    TEXT(type = "text/*"),
    IMAGE(type = "image/*"),
    AUDIO(type = "audio/*"),
    VIDEO(type = "video/*"),
    APPLICATION(type = "application/*"),
}
