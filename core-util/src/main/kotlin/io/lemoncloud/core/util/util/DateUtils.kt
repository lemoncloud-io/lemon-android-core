package io.lemoncloud.core.util.util

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object DateUtils {

    fun convertTimeStampToDate(timeStamp: Long): Date = Date(timeStamp)

    /**
     * Date 타입을 string format 으로 변환
     * @param dateFormat 변환 하고자 하는 형식 (ex. yyyy-MM-dd, HH:mm:ss, yyyy-MM-dd HH:mm:ss, ...)
     * @param timeZone 변환 하고자 하는 TimeZone
     */
    fun Date.toStringFormat(
        dateFormat: String,
        timeZone: String,
        locale: Locale = Locale.getDefault()
    ): String = SimpleDateFormat(dateFormat, locale).apply {
        this.timeZone = TimeZone.getTimeZone(timeZone)
    }.format(/* date = */ this)

    fun Date.toStringFormat(
        locale: Locale,
        dateFormat: DateStyle,
        timeZone: String
    ): String =
        DateFormat.getDateInstance(
            when (dateFormat) {
                DateStyle.SHORT -> DateFormat.SHORT
                DateStyle.MEDIUM -> DateFormat.MEDIUM
                DateStyle.LONG -> DateFormat.LONG
                DateStyle.FULL -> DateFormat.FULL
            }, locale
        ).apply {
            this.timeZone = TimeZone.getTimeZone(timeZone)
        }.format(/* date = */ this)

    enum class DateStyle { SHORT, MEDIUM, LONG, FULL }
}

