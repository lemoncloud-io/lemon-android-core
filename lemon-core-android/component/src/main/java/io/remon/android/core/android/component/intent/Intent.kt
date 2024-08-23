package io.remon.android.core.android.component.intent

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Parcelable
import android.provider.Settings
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
     * [getParcelableExtraExt]
     *
     * intent extra에 포함되어 있는 parcelable 객체를 가져올 때 사용합니다.
     */
    fun <T : Parcelable> Intent.getParcelableExtraExt(key: String, `class`: Class<T>): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            getParcelableExtra(key, `class`)
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
     * [getUrlIntent]
     *
     * url 주소를 포함한 intent를 생성합니다.
     *
     * `startActivity()`와 연계하여 사용합니다.
     */
    val getUrlIntent: (String) -> Intent =
        { url -> intentBuilder().setAction(Intent.ACTION_VIEW).setData(Uri.parse(url)).build() }

    /**
     * [getSettingsIntent]
     *
     * 애플리케이션의 설정으로 이동하는 intent를 생성합니다.
     *
     * `startActivity()`와 연계하여 사용합니다.
     */
    val getSettingsIntent: (Context) -> Intent = { context ->
        intentBuilder().setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).setData(
            Uri.parse("package:${context.packageName}")
        ).build()
    }
}
