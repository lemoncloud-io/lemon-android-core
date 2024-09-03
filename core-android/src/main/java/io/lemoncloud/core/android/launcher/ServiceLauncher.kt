package io.lemoncloud.core.android.launcher

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import io.lemoncloud.core.android.intent.IntentBuilder
import kotlin.reflect.KClass

/**
 * [ServiceLauncher]
 *
 * 서비스(Service) 관련한 초기화 및 시작 작업을 도와주는 클래스
 *
 * @author raine@lemoncloud.io
 */
class ServiceLauncher<T : Service>(
    private val context: Context,
    private val serviceClass: KClass<T>
) {
    private var intent: Intent = Intent(context, serviceClass.java)

    /**
     * [intentBuilder]
     *
     * [Service] 에 대한 Intent를 작업하기 위한 [IntentBuilder]를 생성합니다.
     *
     * [IntentBuilder]를 통해 생성한 `Intent` 를 [updateIntent]를 사용하여 업데이트할 수 있습니다.
     *
     * @see IntentBuilder
     */
    fun intentBuilder(): IntentBuilder = IntentBuilder(context, serviceClass.java)

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

    /**
     * [startService]
     *
     * 서비스를 시작합니다.
     */
    fun startService() {
        context.startService(intent)
    }

    /**
     * [startService]
     *
     * 서비스를 종료합니다.
     */
    fun stopService() {
        context.stopService(intent)
    }

    /**
     * [startForegroundService]
     *
     *  Foreground 서비스를 시작합니다.
     */
    fun startForegroundService(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            context.startForegroundService(intent)
        else
            context.startService(intent)
    }
}
