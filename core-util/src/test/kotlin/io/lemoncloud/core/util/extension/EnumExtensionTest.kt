package io.lemoncloud.core.util.extension

import com.google.common.truth.Truth
import io.lemoncloud.core.util.extension.EnumExtensions.enumValueOfOrNull
import io.lemoncloud.core.util.extension.EnumExtensions.find
import io.lemoncloud.core.util.extension.mock.MockEnum
import org.junit.Test

class EnumExtensionTest {

    @Test
    fun correctEnumValue_enumValueOfOrNull_returnsExpectedEnumValue() {
        val enumValue = "C"

        val result = enumValueOfOrNull<MockEnum>(enumValue)

        Truth.assertThat(result).isEqualTo(MockEnum.C)
    }

    @Test
    fun wrongEnumValue_enumValueOfOrNull_returnsExpectedNull() {
        val enumValue = "F"

        val result = enumValueOfOrNull<MockEnum>(enumValue)

        Truth.assertThat(result).isNull()
    }


    @Test
    fun correctEnumValue_find_returnsExpectedEnumValue() {
        val enumValueId = MockEnum.C.id

        val result = find<MockEnum> { it.id == enumValueId }

        Truth.assertThat(result).isEqualTo(MockEnum.C)
    }

    @Test
    fun wrongEnumValue_find_returnsExpectedNull() {
        val enumValueId = 30

        val result = find<MockEnum> { it.id == enumValueId }

        Truth.assertThat(result).isNull()
    }
}
