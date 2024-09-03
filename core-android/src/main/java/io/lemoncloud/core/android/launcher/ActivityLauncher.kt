package io.lemoncloud.core.android.launcher

import android.app.Activity
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.content.Intent.FLAG_ACTIVITY_NO_HISTORY
import android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
import io.lemoncloud.core.android.intent.IntentBuilder
import kotlin.reflect.KClass

/**
 * [ActivityLauncher]
 *
 * 활동(Activity) 관련한 초기화 및 시작 작업을 도와주는 클래스
 *
 * @author raine@lemoncloud.io
 */
class ActivityLauncher<T : Activity>(
    private val context: Context,
    private val activityClass: KClass<T>
) {
    private var intent: Intent = Intent(context, activityClass.java)

    /**
     * [intentBuilder]
     *
     * [Service] 에 대한 Intent를 작업하기 위한 [IntentBuilder]를 생성합니다.
     *
     * [IntentBuilder]를 통해 생성한 `Intent` 를 [updateIntent]를 사용하여 업데이트할 수 있습니다.
     *
     * @see IntentBuilder
     */
    fun intentBuilder(): IntentBuilder = IntentBuilder(context, activityClass.java)

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
     * [startActivity]
     *
     * 활동을 시작합니다.
     */
    fun startActivity() {
        context.startActivity(intent)
    }

    /**
     * [startActivityNoHistory]
     *
     * 활동이 테스크 스택에 쌓이지 않고 시작됩니다.
     */
    fun startActivityNoHistory(context: Context) {
        context.startActivity(
            intent.also { it.setFlags(FLAG_ACTIVITY_NO_HISTORY) }
        )
    }

    /**
     * [startActivityClearTop]
     *
     * 활동을 테스크 스택 최상위로 올리고 그 사이 테스크 스택을 전부 제거합니다.
     */
    fun startActivityClearTop(context: Context) {
        context.startActivity(
            intent.also { it.setFlags(FLAG_ACTIVITY_CLEAR_TOP) }
        )
    }

    /**
     * [startActivitySingleTop]
     *
     * 활동을 태스크 스택 최상위로 올림 만약 테스크 내에 동일한 활동이 존재하지 않을경우, 새로 생성합니다.
     */
    fun startActivitySingleTop(context: Context) {
        context.startActivity(
            intent.also { it.setFlags(FLAG_ACTIVITY_SINGLE_TOP) }
        )
    }
}
