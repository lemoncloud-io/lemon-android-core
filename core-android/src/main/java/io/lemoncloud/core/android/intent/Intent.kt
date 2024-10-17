package io.lemoncloud.core.android.intent

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Parcelable
import android.provider.Settings
import io.lemoncloud.core.android.model.MIMEType
import java.io.Serializable

/**
 * [Intent]
 *
 * Intent 유틸리티
 *
 * @author raine@lemoncloud.io
 */
object Intent {

    /**
     * [intentBuilder]
     *
     * 인텐트 빌더를 생성합니다.
     *
     * context를 포함하지 않는 인텐트의 경우 해당 메서드를 사용합니다.
     *
     * @see IntentBuilder
     */
    fun intentBuilder(): IntentBuilder = IntentBuilder()

    /**
     * [intentBuilder]
     *
     * 인텐트 빌더를 생성합니다.
     *
     * intent 타겟을 포함할 경우 헤당 메서드를 사용합니다.
     *
     * @see IntentBuilder
     */
    fun intentBuilder(context: Context, `class`: Class<*>): IntentBuilder = IntentBuilder(context, `class`)

    /**
     * [intentBuilder]
     *
     * 인텐트 빌더를 생성합니다.
     *
     * intent 타겟을 포함할 경우 헤당 메서드를 사용합니다.
     *
     * @see IntentBuilder
     */
    fun intentBuilder(intent: Intent): IntentBuilder = IntentBuilder(intent)

    /**
     * [getParcelableExtraExt]
     *
     * intent extra에 포함되어 있는 parcelable 객체를 가져올 때 사용합니다.
     */
    fun <T : Parcelable> Intent.getParcelableExtraExt(key: String, `class`: Class<T>): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) getParcelableExtra(key, `class`)
        else getParcelableExtra(key)
    }

    /**
     * [getSerializableExtraExt]
     *
     * intent extra에 포함되어 있는 serializable 객체를 가져올 때 사용합니다.
     */
    @Suppress("UNCHECKED_CAST")
    fun <T : Serializable> Intent.getSerializableExtraExt(key: String, `class`: Class<T>): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            this.getSerializableExtra(key, `class`)
        } else {
            this.getSerializableExtra(key) as T?
        }
    }

    /**
     * [getViewUrlIntent]
     *
     * url 주소의 데이터를 보여주는 intent를 생성합니다.
     * `startActivity()`와 연계하여 사용합니다.
     *
     *  @see Context.startActivity
     */
    fun getViewUrlIntent(url: String): Intent =
        intentBuilder()
            .setAction(Intent.ACTION_VIEW)
            .setData(Uri.parse(url))
            .build()

    /**
     * [getApplicationDetailSettingsIntent]
     *
     * 애플리케이션 설정을 보여주는 intent를 생성합니다.
     * `startActivity()`와 연계하여 사용합니다.
     *
     *  @see Context.startActivity
     */
    fun getApplicationDetailSettingsIntent(context: Context) =
        intentBuilder()
            .setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            .setData(Uri.parse("package:${context.packageName}"))
            .build()
    /**
     * [getSendTextIntent]
     *
     * 문자를 전송하는 intent를 생성합니다.
     * `startActivity()`와 연계하여 사용합니다.
     *
     * @see Context.startActivity
     */
    fun getSendTextIntent(text: String) =
        intentBuilder()
            .setAction(Intent.ACTION_SEND)
            .setType(MIMEType.Text.type)
            .putExtras(Intent.EXTRA_TEXT to text)
            .build()
}
