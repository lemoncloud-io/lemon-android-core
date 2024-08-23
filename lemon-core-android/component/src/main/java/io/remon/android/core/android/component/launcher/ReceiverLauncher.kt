package io.remon.android.core.android.component.launcher

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import io.remon.android.core.android.component.intent.IntentBuilder
import kotlin.reflect.KClass

/**
 * [ReceiverLauncher]
 *
 * 수신자(Receiver) 관련한 초기화 및 시작 작업을 도와주는 클래스
 *
 * @author raine@lemoncloud.io
 */
class ReceiverLauncher<T : BroadcastReceiver>(
    private val context: Context,
    private val receiverClass: KClass<T>
) {
    private var intent: Intent = Intent(context, receiverClass.java)

    /**
     * [intentBuilder]
     *
     * [Service] 에 대한 Intent를 작업하기 위한 [IntentBuilder]를 생성합니다.
     *
     * [IntentBuilder]를 통해 생성한 `Intent` 를 [updateIntent]를 사용하여 업데이트할 수 있습니다.
     *
     * @see IntentBuilder
     */
    fun intentBuilder(): IntentBuilder = IntentBuilder(context, receiverClass.java)

    /**
     * [updateIntent]
     *
     * 인텐트에 별도 설정을 추가하여 업데이트 해야하는 상황에서 사용합니다
     *
     * [intentBuilder] 를 통해 IntentBuilder를 연결할 수 있습니다.
     *
     * @see IntentBuilder
     */
    fun updateIntent(intent: Intent) {
        this.intent = intent
    }
}
