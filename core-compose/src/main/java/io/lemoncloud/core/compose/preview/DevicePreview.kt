package io.lemoncloud.core.compose.preview

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices.FOLDABLE
import androidx.compose.ui.tooling.preview.Devices.TABLET
import androidx.compose.ui.tooling.preview.Preview

/**
 * 일반적인 디바이스 화면에서  @[Composable] 컴포넌트를 보여주기 위한 애노테이션
 */
@Retention(AnnotationRetention.BINARY)
@Target(
    AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.FUNCTION
)
@Preview(
    name = "Android Large Night",
    group = "Phone Device",
    device = "spec:width=412dp,height=846dp,dpi=480",
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Android Large Day",
    group = "Phone Device",
    device = "spec:width=412dp,height=846dp,dpi=480",
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Android Small Night",
    group = "Phone Device",
    device = "spec:width=360dp,height=640dp,dpi=480",
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Android Small Day",
    group = "Phone Device",
    device = "spec:width=360dp,height=640dp,dpi=480",
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
annotation class PhoneDevicePreview

/**
 * 폴더블 디바이스 화면에서  @[Composable] 컴포넌트를 보여주기 위한 애노테이션
 */
@Retention(AnnotationRetention.BINARY)
@Target(
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.FUNCTION
)
@Preview(
    name = "Foldable Night",
    group = "Foldable Device",
    device = FOLDABLE,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Foldable Day",
    group = "Foldable Device",
    device = FOLDABLE,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
annotation class FoldableDevicePreview

/**
 * 테블릿 디바이스 화면에서  @[Composable] 컴포넌트를 보여주기 위한 애노테이션
 */
@Retention(AnnotationRetention.BINARY)
@Target(
    AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.FUNCTION
)
@Preview(
    name = "Tablet Night",
    group = "Tablet Device",
    device = "spec:width=1280dp,height=800dp,dpi=240",
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Tablet Day",
    group = "Tablet Device",
    device = TABLET,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
annotation class TabletDevicePreview

/**
 * 여러 형태의 디바이스 화면에서 @[Composable] 컴포넌트를 보여주기 위한 애노테이션
 */
@PhoneDevicePreview
@FoldableDevicePreview
@TabletDevicePreview
annotation class MultiDevicePreview
