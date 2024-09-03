package io.lemoncloud.core.compose.preview

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

/**
 * A ComponentPreview annotation for displaying a @[Composable] method in a component.
 */
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.FUNCTION)
@Preview(name = "Night", group = "Component", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Day", group = "Component", uiMode = Configuration.UI_MODE_NIGHT_NO)
annotation class ComponentPreview
