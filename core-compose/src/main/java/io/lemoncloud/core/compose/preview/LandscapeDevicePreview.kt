package io.lemoncloud.core.compose.preview

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

/**
 * A LandscapeDevicePreview annotation for displaying a @[Composable] method in a component using a phone device in landscape mode.
 */
@Retention(AnnotationRetention.BINARY)
@Target(
    AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.FUNCTION
)
@Preview(
    name = "Landscape Android Large Night",
    group = "Landscape Phone Device",
    device = "spec:width=412dp,height=846dp,dpi=480,orientation=landscape",
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Landscape Android Large Day",
    group = "Landscape Phone Device",
    device = "spec:width=412dp,height=846dp,dpi=480,orientation=landscape",
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Landscape Android Small Night",
    group = "Landscape Phone Device",
    device = "spec:width=360dp,height=640dp,dpi=480,orientation=landscape",
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Landscape Android Small Day",
    group = "Landscape Phone Device",
    device = "spec:width=360dp,height=640dp,dpi=480,orientation=landscape",
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
annotation class LandscapeDevicePreview
