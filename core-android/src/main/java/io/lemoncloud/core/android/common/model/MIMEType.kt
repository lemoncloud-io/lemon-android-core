package io.lemoncloud.core.android.common.model

enum class MIMEType(val type: String) {
    All(type = "*/*"),
    Text(type = "text/*"),
    Image(type = "image/*"),
    Audio(type = "audio/*"),
    Video(type = "video/*"),
    Application(type = "application/*"),
}
