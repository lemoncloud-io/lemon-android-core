package io.lemoncloud.core.android.launcher

import android.app.Activity
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.content.Intent.FLAG_ACTIVITY_NO_HISTORY
import android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
import io.lemoncloud.core.android.intent.IntentBuilder
import io.lemoncloud.core.android.intent.IntentUtils
import kotlin.reflect.KClass

/**
 * 활동(Activity) 관련한 초기화 및 시작 작업을 도와줍니다.
 */
class ActivityLauncher<T : Activity>(
    private val context: Context,
    private val activityClass: KClass<T>
) {
    private var intent: Intent = Intent(context, activityClass.java)

    /**
     * [Service] 에 대한 Intent를 작업하기 위한 [IntentBuilder]를 생성합니다.
     *
     * [IntentBuilder]를 통해 생성한 `Intent` 를 [updateIntent]를 사용하여 업데이트할 수 있습니다.
     *
     * @see IntentBuilder
     */
    fun intentBuilder(): IntentBuilder = IntentUtils.intentBuilder(context, activityClass.java)

    /**
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
     * 활동을 시작합니다.
     */
    fun startActivity() {
        context.startActivity(intent)
    }

    /**
     * 활동을 새로운 테스크로 시작합니다. 단, 기존 태스크 중에 동일 Affinity를 가진 테스크가 있을 경우 해당 테스크에서 활동이 실행됩니다.
     */
    fun startActivityWithNewTask(context: Context) {
        context.startActivity(
            intent.apply { setFlags(FLAG_ACTIVITY_NEW_TASK) }
        )
    }

    /**
     * 활동이 테스크 스택에 쌓이지 않고 시작됩니다.
     */
    fun startActivityWithNoHistory(context: Context) {
        context.startActivity(
            intent.apply { setFlags(FLAG_ACTIVITY_NO_HISTORY) }
        )
    }

    /**
     * 활동을 테스크 스택 최상위로 올리고 그 사이 테스크 스택을 전부 제거합니다.
     */
    fun startActivityWithClearTop(context: Context) {
        context.startActivity(
            intent.apply { setFlags(FLAG_ACTIVITY_CLEAR_TOP) }
        )
    }

    /**
     * 활동을 태스크 스택 최상위로 올립니다.
     * 만약 테스크 내에 동일한 활동이 존재하지 않을 경우, 새로 생성합니다.
     */
    fun startActivityWithSingleTop(context: Context) {
        context.startActivity(
            intent.apply { setFlags(FLAG_ACTIVITY_SINGLE_TOP) }
        )
    }
}
