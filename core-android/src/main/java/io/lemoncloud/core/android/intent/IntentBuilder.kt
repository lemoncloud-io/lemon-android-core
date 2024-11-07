package io.lemoncloud.core.android.intent

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Parcelable
import androidx.core.os.bundleOf
import io.lemoncloud.core.android.common.model.MIMEType
import java.io.Serializable

/**
 * intent 생성을 도와줍니다.
 */
class IntentBuilder() {

    private var intent: Intent = Intent()

    constructor(context: Context, `class`: Class<*>) : this() {
        intent = Intent(context, `class`)
    }

    constructor(intent: Intent) : this() {
        this.intent = intent
    }

    /**
     * intent 에 실어서 보낼 extra를 정의합니다.
     */
    fun <T : Parcelable> putExtra(extra: T, key: String = DEFAULT_KEY) = apply {
        intent.putExtra(key, extra)
    }

    fun <T : Serializable> putExtra(extra: T, key: String = DEFAULT_KEY) = apply {
        intent.putExtra(key, extra)
    }

    fun <T : Parcelable> putExtra(extra: ArrayList<T>, key: String = DEFAULT_KEY) = apply {
        intent.putExtra(key, extra)
    }

    fun putExtra(extra: String, key: String = DEFAULT_KEY) = apply {
        intent.putExtra(key, extra)
    }

    fun putExtra(extra: Long, key: String = DEFAULT_KEY) = apply {
        intent.putExtra(key, extra)
    }

    fun putExtra(extra: Int, key: String = DEFAULT_KEY) = apply {
        intent.putExtra(key, extra)
    }

    fun putExtra(extra: Float, key: String = DEFAULT_KEY) = apply {
        intent.putExtra(key, extra)
    }

    fun putExtra(extra: Boolean, key: String = DEFAULT_KEY) = apply {
        intent.putExtra(key, extra)
    }

    /**
     * 번들 객체를 통해 intent에 extra를 추가합니다.
     * @param pairs key,value 쌍
     */
    fun putExtras(vararg pairs: Pair<String, Any?>) = apply {
        intent.putExtras(bundleOf(*pairs))
    }

    /**
     * 인텐츠 액션을 설정합니다.
     */
    fun setAction(action: String) = apply {
        intent.setAction(action)
    }

    /**
     * 인텐트 타입을 설정합니다.
     * @param mimetype
     */
    fun setType(type: String) = apply {
        intent.setType(type)
    }

    /**
     * 인텐트 타입을 설정합니다.
     */
    fun setType(mimeType: MIMEType) = apply {
        intent.setType(mimeType.type)
    }

    /**
     * uri 타입의 데이터 설정
     */
    fun setData(uri: Uri) = apply {
        intent.setData(uri)
    }

    /**
     * 플래그 설정
     */
    fun setFlags(flag: Int) = apply {
        intent.setFlags(flag)
    }

    /**
     * 플래그 추가
     */
    fun addFlags(flag: Int) = apply {
        intent.addFlags(flag)
    }

    /**
     * IntentBuilder를 통해 조합한 intent 객체를 반환합니다.
     */
    fun build(): Intent = intent

    companion object {
        const val DEFAULT_KEY = "KEY"
    }
}
