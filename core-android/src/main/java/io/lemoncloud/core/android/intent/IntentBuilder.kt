package io.lemoncloud.core.android.intent

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Parcelable
import androidx.core.os.bundleOf
import io.lemoncloud.core.android.common.model.MIMEType
import java.io.Serializable

/**
 * [IntentBuilder]
 *
 * intent 생성을 도와주는 Builder 클래스
 *
 * @author raine@lemoncloud.io
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
     * [putExtras]
     *
     * intent 에 담아서 보낼 데이터인, extra를 정의합니다.
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
     * [putExtras]
     *
     * 번들 단위로 데이터 추가
     */
    fun putExtras(vararg pairs: Pair<String, Any?>) = apply {
        intent.putExtras(bundleOf(*pairs))
    }

    /**
     * [setAction]
     *
     * action 추가
     */
    fun setAction(action: String) = apply {
        intent.setAction(action)
    }

    /**
     * [setType]
     *
     * type 설정
     */
    fun setType(type: String) = apply {
        intent.setType(type)
    }

    /**
     * [setType]
     *
     * type 설정
     */
    fun setType(mimeType: MIMEType) = apply {
        intent.setType(mimeType.type)
    }

    /**
     * [setAction]
     *
     * uri 형태 데이터 추가
     */
    fun setData(uri: Uri) = apply {
        intent.setData(uri)
    }

    /**
     * [setFlags]
     *
     * 플래그 설정
     */
    fun setFlags(flag: Int) = apply {
        intent.setFlags(flag)
    }

    /**
     * [addFlags]
     *
     * 플래그 추가
     */
    fun addFlags(flag: Int) = apply {
        intent.addFlags(flag)
    }

    /**
     * [build]
     *
     * IntentBuilder를 통해 조합한 intent 객체를 반환합니다.
     */
    fun build(): Intent = intent

    companion object {
        const val DEFAULT_KEY = "KEY"
    }
}
