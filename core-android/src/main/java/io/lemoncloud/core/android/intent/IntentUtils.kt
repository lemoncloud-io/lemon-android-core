package io.lemoncloud.core.android.intent

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Parcelable
import android.provider.Settings
import io.lemoncloud.core.android.common.model.MIMEType
import java.io.Serializable

/**
 * [IntentUtils]
 * - Intent 유틸리티
 */
object IntentUtils {

    /**
     * [getParcelableExtraExt]
     * - intent extra에 포함되어 있는 parcelable 객체를 가져올 때 사용합니다.
     */
    fun <T : Parcelable> Intent.getParcelableExtraExt(key: String, `class`: Class<T>): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) getParcelableExtra(key, `class`)
        else getParcelableExtra(key)
    }

    /**
     * [getSerializableExtraExt]
     * - intent extra에 포함되어 있는 serializable 객체를 가져올 때 사용합니다.
     */
    @Suppress("UNCHECKED_CAST")
    fun <T : Serializable> Intent.getSerializableExtraExt(key: String, `class`: Class<T>): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) getSerializableExtra(key, `class`)
        else getSerializableExtra(key) as T?
    }

    /**
     * [getViewUrlIntent]
     * - url 주소를 불러오는 intent를 생성합니다.
     * - `startActivity()`와 연계하여 사용합니다.
     *
     *  @see Context.startActivity
     */
    fun getViewUrlIntent(url: String): Intent =
        IntentBuilder.builder()
            .setAction(Intent.ACTION_VIEW)
            .setData(Uri.parse(url))
            .build()

    /**
     * [getApplicationDetailSettingsIntent]
     * - 애플리케이션 설정을 보여주는 intent를 생성합니다.
     * - `startActivity()`와 연계하여 사용합니다.
     *
     *  @see Context.startActivity
     */
    fun getApplicationDetailSettingsIntent(context: Context): Intent =
        IntentBuilder.builder()
            .setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            .setData(Uri.parse("package:${context.packageName}"))
            .build()

    /**
     * [getSendTextIntent]
     * - 문자를 전송하는 intent를 생성합니다.
     * - `startActivity()`와 연계하여 사용합니다.
     *
     * @see Context.startActivity
     */
    fun getSendTextIntent(text: String): Intent =
        IntentBuilder.builder()
            .setAction(Intent.ACTION_SEND)
            .setType(MIMEType.TEXT.type)
            .putExtras(Intent.EXTRA_TEXT to text)
            .build()

    /**
     * [getMarketIntent]
     * - 스토어로 이동하는 intent를 생성합니다.
     * - `startActivity()`와 연계하여 사용합니다.
     *
     * @see Context.startActivity
     */
    fun getMarketIntent(context: Context): Intent =
        IntentBuilder.builder()
            .setAction(Intent.ACTION_VIEW)
            .setData(Uri.parse("market://details?id=${context.packageName}"))
            .build()
}
