package io.lemoncloud.core.util.util

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object DateUtils {

    /**
     * timeStamp 를 [Date]로 변경할 때 사용합니다.
     * @param timeStamp 타임스탬프
     */
    fun convertTimeStampToDate(timeStamp: Long): Date = Date(timeStamp)

    /**
     * Date 타입을 string format 으로 변환할 때 사용합니다.
     * @param dateFormat 변환 하고자 하는 형식 (ex. yyyy-MM-dd, HH:mm:ss, yyyy-MM-dd HH:mm:ss, ...)
     * @param timeZone 변환 하고자 하는 TimeZone (ex. Asia/Seoul)
     */
    fun Date.toStringFormat(
        dateFormat: String,
        timeZone: String,
        locale: Locale = Locale.getDefault()
    ): String = SimpleDateFormat(dateFormat, locale).apply {
        this.timeZone = TimeZone.getTimeZone(timeZone)
    }.format(/* date = */ this)

    /**
     * Date 타입을 주어진 특정 날짜 포멧으로 변환할 때 사용합니다.
     * @param dateStyle 변환 하고자 하는 날짜 스타일
     * @param timeZone 변환 하고자 하는 TimeZone (ex. Asia/Seoul)
     * @param locale 변환 하고자 하는 Locale
     * @see DateStyle
     */
    fun Date.toStringFormat(
        dateStyle: DateStyle,
        timeZone: String,
        locale: Locale = Locale.getDefault(),
    ): String =
        DateFormat.getDateInstance(
            when (dateStyle) {
                DateStyle.SHORT -> DateFormat.SHORT
                DateStyle.MEDIUM -> DateFormat.MEDIUM
                DateStyle.LONG -> DateFormat.LONG
                DateStyle.FULL -> DateFormat.FULL
            }, locale
        ).apply {
            this.timeZone = TimeZone.getTimeZone(timeZone)
        }.format(/* date = */ this)

    /**
     * [DateFormat]의 날짜 포멧 상수 값과 연동하기 위한 타입
     * @property DateStyle.SHORT  [DateFormat.SHORT]
     * @property DateStyle.MEDIUM  [DateFormat.MEDIUM]
     * @property DateStyle.LONG  [DateFormat.LONG]
     * @property DateStyle.FULL  [DateFormat.FULL]
     */
    enum class DateStyle { SHORT, MEDIUM, LONG, FULL }
}

