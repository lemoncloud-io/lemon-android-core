package io.lemoncloud.core.android.launcher

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import io.lemoncloud.core.android.common.model.MIMEType

object ActivityResultLauncher {

    /**
     * @param resultCallback 외부 액티비티 실행 결과에 대한 콜백 처리
     */
    fun ComponentActivity.startActivityForResult(
        resultCallback: (ActivityResult) -> Unit
    ): ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            resultCallback(it)
        }

    /**
     * @param resultCallback 권한별 허용 여부에 대한 콜백 처리
     */
    fun ComponentActivity.registerMultiplePermissionForResult(
        resultCallback: (Map<String, Boolean>) -> Unit
    ): ActivityResultLauncher<Array<String>> =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            resultCallback(it)
        }

    /**
     * @param resultCallback 권한 허용 여부에 대한 콜백 처리
     */
    fun ComponentActivity.registerPermissionForResult(
        resultCallback: (Boolean) -> Unit
    ): ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            resultCallback(it)
        }

    /**
     * @param resultCallback 사진 촬영을 통해 받아온 [Bitmap]에 대한 콜백 처리
     */
    fun ComponentActivity.registerTakePicturePreviewForResult(
        resultCallback: (Bitmap?) -> Unit
    ): ActivityResultLauncher<Void?> =
        registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
            resultCallback(it)
        }

    /**
     * @param resultCallback uri 저장 완료 여부 대한 콜백 처리
     */
    fun ComponentActivity.registerTakePictureForResult(
        resultCallback: (Boolean) -> Unit
    ): ActivityResultLauncher<Uri> =
        registerForActivityResult(ActivityResultContracts.TakePicture()) {
            resultCallback(it)
        }

    /**
     * @param resultCallback uri 저장 완료 여부 대한 콜백 처리
     */
    fun ComponentActivity.registerCaptureVideoForResult(
        resultCallback: (Boolean) -> Unit
    ): ActivityResultLauncher<Uri> =
        registerForActivityResult(ActivityResultContracts.CaptureVideo()) {
            resultCallback(it)
        }

    /**
     * @param resultCallback uri에 대한 콜백 처리
     */
    fun ComponentActivity.registerPickContactForResult(
        resultCallback: (Uri?) -> Unit
    ): ActivityResultLauncher<Void?> =
        registerForActivityResult(ActivityResultContracts.PickContact()) {
            resultCallback(it)
        }

    /**
     * @param resultCallback uri에 대한 콜백 처리
     */
    fun ComponentActivity.registerGetContentForResult(
        resultCallback: (Uri?) -> Unit
    ): ActivityResultLauncher<String?> =
        registerForActivityResult(ActivityResultContracts.GetContent()) {
            resultCallback(it)
        }

    /**
     * @param resultCallback uri 리스트에 대한 콜백 처리
     */
    fun ComponentActivity.registerGetMultipleContentsForResult(
        resultCallback: (List<Uri>) -> Unit
    ): ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.GetMultipleContents()) {
            resultCallback(it)
        }

    /**
     * @param resultCallback uri에 대한 콜백 처리
     */
    fun ComponentActivity.registerOpenDocumentForResult(
        resultCallback: (Uri?) -> Unit
    ): ActivityResultLauncher<Array<String>> =
        registerForActivityResult(ActivityResultContracts.OpenDocument()) {
            resultCallback(it)
        }

    /**
     * @param resultCallback uri 리스트에 대한 콜백 처리
     */
    fun ComponentActivity.registerOpenMultipleDocumentsForResult(
        resultCallback: (List<Uri>) -> Unit
    ): ActivityResultLauncher<Array<String>> =
        registerForActivityResult(ActivityResultContracts.OpenMultipleDocuments()) {
            resultCallback(it)
        }

    /**
     * @param resultCallback uri에 대한 콜백 처리
     */
    fun ComponentActivity.registerOpenDocumentTreeForResult(
        resultCallback: (Uri?) -> Unit
    ): ActivityResultLauncher<Uri?> =
        registerForActivityResult(ActivityResultContracts.OpenDocumentTree()) {
            resultCallback(it)
        }

    /**
     * @param resultCallback uri에 대한 콜백 처리
     */
    fun ComponentActivity.registerCreateDocumentForResult(
        mimeType: MIMEType,
        resultCallback: (Uri?) -> Unit
    ): ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.CreateDocument(mimeType.type)) {
            resultCallback(it)
        }

    /**
     * @param resultCallback uri에 대한 콜백 처리
     */
    fun ComponentActivity.registerCreateDocumentForResult(
        mimeType: String,
        resultCallback: (Uri?) -> Unit
    ): ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.CreateDocument(mimeType)) {
            resultCallback(it)
        }

    /**
     * @param resultCallback uri에 대한 콜백 처리
     */
    fun ComponentActivity.registerPickVisualMediaForResult(
        resultCallback: (Uri?) -> Unit
    ): ActivityResultLauncher<PickVisualMediaRequest> =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) {
            resultCallback(it)
        }

    /**
     * @param resultCallback uri 리스트에 대한 콜백 처리
     */
    fun ComponentActivity.registerPickVisualMediaForResult(
        resultCallback: (List<Uri>) -> Unit
    ): ActivityResultLauncher<PickVisualMediaRequest> =
        registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia()) {
            resultCallback(it)
        }
}
