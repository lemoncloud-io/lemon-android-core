package io.lemoncloud.core.util.extension

import com.google.common.truth.Truth
import io.lemoncloud.core.util.util.DateUtils.convertTimestampToDateFormatString
import io.lemoncloud.core.util.util.DateUtils.toDateFormatString
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import org.junit.Test

class DateUtilTest {

    @Test
    fun instant_toDateFormatString_returnsExpectedDateFormatString() {

        val instant = Instant.fromEpochMilliseconds(1730774244000)

        val result = instant.toDateFormatString(format = "yyyy-MM-dd HH:mm:ss")

        Truth.assertThat(result)
            .isEqualTo("2024-11-05 11:37:24")
    }


    @Test
    fun localDateTime_toDateFormatString_returnsExpectedDateFormatString() {
        val localDateTime = LocalDateTime(
            year = 2024,
            monthNumber = 11,
            dayOfMonth = 5,
            hour = 11,
            minute = 37,
            second = 24,
        )

        val result = localDateTime.toDateFormatString(format = "yyyy-MM-dd HH:mm:ss")

        Truth.assertThat(result)
            .isEqualTo("2024-11-05 11:37:24")
    }

    @Test
    fun timestamp_convertTimestampToDateFormatString_returnsExpectedDateFormatString() {
        val timestamp = 1730774244000 //Tue Nov 05 11:37:24 KST 2024

        val result = convertTimestampToDateFormatString(timestamp = timestamp, format = "yyyy-MM-dd")

        Truth.assertThat(result)
            .isEqualTo("2024-11-05")
    }
}
