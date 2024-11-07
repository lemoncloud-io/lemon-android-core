package io.lemoncloud.core.android.permission

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.annotation.IntRange
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object PermissionUtils {

    /**
     * 권한 승인 여부를 확인합니다.
     * @param context
     * @param permission 권한
     */
    fun checkPermission(context: Context, permission: String): Boolean =
        ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED

    /**
     * 전체 권한 승인 여부를 확인합니다.
     * @param context
     * @param permissions 권한 목록
     */
    fun checkPermissions(context: Context, permissions: List<String>): List<Boolean> =
        permissions.map { checkPermission(context, it) }

    /**
     * 권한을 요청합니다.
     * @param activity
     * @param permissions 요청할 권한 목록
     */
    fun requestPermission(
        activity: Activity,
        permissions: Array<String>,
        @IntRange(from = 0) requestCode: Int
    ): Unit = ActivityCompat.requestPermissions(activity, permissions, requestCode)

    /**
     * 권한을 요청
     * @param activity
     * @param permissions 요청할 권한 목록
     */
    fun requestPermission(
        activity: Activity,
        permissions: List<String>,
        @IntRange(from = 0) requestCode: Int
    ): Unit = ActivityCompat.requestPermissions(activity, permissions.toTypedArray(), requestCode)
}
