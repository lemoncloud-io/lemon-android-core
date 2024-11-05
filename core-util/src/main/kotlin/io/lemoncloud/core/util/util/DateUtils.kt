package io.lemoncloud.core.util.util

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toJavaInstant
import java.time.format.DateTimeFormatter

object DateUtils {

    /**
     * [Instant] 타입을 주어진 특정 날짜 포멧으로 변환할 때 사용합니다.
     * @param format 변환 하고자 하는 날짜 스타일 포멧 (ex. yyyy-MM-dd)
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun Instant.toDateFormatString(
        format: String,
    ): String = DateTimeFormatter.ofPattern(format).format(toJavaInstant())

    /**
     * [LocalDateTime] 타입을 주어진 특정 날짜 포멧으로 변환할 때 사용합니다.
     * @param format 변환 하고자 하는 날짜 스타일 포멧 (ex. yyyy-MM-dd)
     * @param timeZone 사용 하고자 하는 TimeZone
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun LocalDateTime.toDateFormatString(
        format: String,
        timeZone: TimeZone = TimeZone.currentSystemDefault()
    ): String = DateTimeFormatter.ofPattern(format).format(toInstant(timeZone).toJavaInstant())
}

