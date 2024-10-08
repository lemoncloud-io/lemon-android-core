package io.lemoncloud.core.android.intent

import android.app.PendingIntent
import android.content.Context
import android.content.Intent

/**
 * [PendingIntent]
 *
 * PendingIntent 관련 유틸리티
 *
 * @author raine@lemoncloud.io
 */
object PendingIntent {

    /**
     * [getActivityPendingIntent]
     *
     * Activity를 포함한 pendingIntent 생성
     *
     * [PendingIntent.FLAG_CANCEL_CURRENT]
     * 이전에 생성한 PendingIntent 취소 후 새로 생성함
     *
     * [PendingIntent.FLAG_NO_CREATE]
     * 이미 생성된 PendingIntent 가 있을 경우 재사용 없을 경우, null 반환
     *
     * [PendingIntent.FLAG_ONE_SHOT]
     * 일회성 사용
     *
     * [PendingIntent.FLAG_UPDATE_CURRENT]
     * 이미 생성된 PendingIntent 가 있을 경우 extraData를 교체함
     *
     * [PendingIntent.FLAG_IMMUTABLE]
     * PendingIntent를 수정할 수 없도록 함 (보안 이슈)
     */
    private fun getActivityPendingIntent(
        context: Context,
        intent: Intent,
        requestCode: Int,
        flags: Int = PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    ): PendingIntent = PendingIntent.getActivity(context, requestCode, intent, flags)

    /**
     * [getActivitiesPendingIntent]
     *
     * 여러 개의 Activity를 포함한 pendingIntent 생성
     *
     * [PendingIntent.FLAG_CANCEL_CURRENT]
     * 이전에 생성한 PendingIntent 취소 후 새로 생성함
     *
     * [PendingIntent.FLAG_NO_CREATE]
     * 이미 생성된 PendingIntent 가 있을 경우 재사용 없을 경우, null 반환
     *
     * [PendingIntent.FLAG_ONE_SHOT]
     * 일회성 사용
     *
     * [PendingIntent.FLAG_UPDATE_CURRENT]
     * 이미 생성된 PendingIntent 가 있을 경우 extraData를 교체함
     *
     * [PendingIntent.FLAG_IMMUTABLE]
     * PendingIntent를 수정할 수 없도록 함 (보안 이슈)
     */
    private fun getActivitiesPendingIntent(
        context: Context,
        intents: Array<Intent>,
        requestCode: Int,
        flags: Int = PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    ): PendingIntent = PendingIntent.getActivities(context, requestCode, intents, flags)

    /**
     * [getBroadcastPendingIntent]
     *
     * Broadcast를 포함한 pendingIntent 생성
     *
     * [PendingIntent.FLAG_CANCEL_CURRENT]
     * 이전에 생성한 PendingIntent 취소 후 새로 생성함
     *
     * [PendingIntent.FLAG_NO_CREATE]
     * 이미 생성된 PendingIntent 가 있을 경우 재사용 없을 경우, null 반환
     *
     * [PendingIntent.FLAG_ONE_SHOT]
     * 일회성 사용
     *
     * [PendingIntent.FLAG_UPDATE_CURRENT]
     * 이미 생성된 PendingIntent 가 있을 경우 extraData를 교체함
     *
     * [PendingIntent.FLAG_IMMUTABLE]
     * PendingIntent를 수정할 수 없도록 함 (보안 이슈)
     */
    private fun getBroadcastPendingIntent(
        context: Context,
        intent: Intent,
        requestCode: Int,
        flags: Int = PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    ): PendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, flags)

    /**
     * [getServicePendingIntent]
     *
     * Serivce를 포함한 pendingIntent 생성
     *
     * [PendingIntent.FLAG_CANCEL_CURRENT]
     * 이전에 생성한 PendingIntent 취소 후 새로 생성함
     *
     * [PendingIntent.FLAG_NO_CREATE]
     * 이미 생성된 PendingIntent 가 있을 경우 재사용 없을 경우, null 반환
     *
     * [PendingIntent.FLAG_ONE_SHOT]
     * 일회성 사용
     *
     * [PendingIntent.FLAG_UPDATE_CURRENT]
     * 이미 생성된 PendingIntent 가 있을 경우 extraData를 교체함
     *
     * [PendingIntent.FLAG_IMMUTABLE]
     * PendingIntent를 수정할 수 없도록 함 (보안 이슈)
     */
    private fun getServicePendingIntent(
        context: Context,
        intent: Intent,
        requestCode: Int,
        flags: Int = PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    ): PendingIntent = PendingIntent.getService(context, requestCode, intent, flags)
}
