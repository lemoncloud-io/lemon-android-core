package io.lemon.android.buildSystem.extensions

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.CommonExtension
import io.lemon.android.buildSystem.extensions.type.FlavorType


fun configureFlavor(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
    flavorList: List<FlavorType>,
) {
    commonExtension.apply {
        flavorDimensions += flavorList.map { it.dimension.name }.distinct()
        productFlavors {
            flavorList.forEach {
                create(it.name) {
                    dimension = it.dimension.name

                    // Set build config field
                    it.buildConfigField.forEach { field ->
                        buildConfigField(
                            type = field.type.field,
                            name = field.name,
                            value = "${field.value}"
                        )
                    }

                    // Set resource value field
                    it.resourceValue.forEach { res ->
                        resValue(
                            type = res.type.field,
                            name = res.name,
                            value = "${res.value}"
                        )
                    }

                    // Set manifest place holders
                    addManifestPlaceholders(it.manifestPlaceholder)

                    if (this@apply is ApplicationExtension && this is ApplicationProductFlavor) {
                        if (it.suffix != null) applicationIdSuffix = it.suffix
                        versionCode = it.versionCode
                        versionName = it.versionName
                    }
                }
            }
        }
    }
}