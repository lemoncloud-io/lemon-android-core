package io.lemon.core.compose.util.modifier

import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.semantics.Role
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Click interaction 이 연속적으로 발생하는 것을 방지하는 확장자
 */
fun Modifier.throttleClickable(
    onClick: () -> Unit,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    coroutineDispatcher: CoroutineDispatcher = Dispatchers.Main,
    throttleTime: Long = 250L,
) = composed {
    val coroutineScope = rememberCoroutineScope { coroutineDispatcher }
    var lastEmissionTime: Long by remember { mutableLongStateOf(0L) }

    clickable(
        onClick = {
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastEmissionTime >= throttleTime) {
                coroutineScope.launch {
                    lastEmissionTime = currentTime
                    onClick()
                }
            }
            lastEmissionTime = currentTime
        },
        enabled = enabled,
        onClickLabel = onClickLabel,
        role = role,
    )
}

/**
 * Click interaction 이 연속적으로 발생하는 것을 방지하는 확장자
 */
fun Modifier.throttleClickable(
    onClick: () -> Unit,
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    indication: Indication? = null,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    coroutineDispatcher: CoroutineDispatcher = Dispatchers.Main,
    throttleTime: Long = 250L,
) = composed {
    val coroutineScope = rememberCoroutineScope { coroutineDispatcher }
    var lastEmissionTime: Long by remember { mutableLongStateOf(0L) }

    noRippleClickable(
        onClick = {
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastEmissionTime >= throttleTime) {
                coroutineScope.launch {
                    lastEmissionTime = currentTime
                    onClick()
                }
            }
            lastEmissionTime = currentTime
        },
        interactionSource = remember { interactionSource },
        indication = indication,
        enabled = enabled,
        onClickLabel = onClickLabel,
        role = role
    )
}

/**
 * Click interaction 중 발생하는 시각 이펙트를 제거하는 확장자
 */
fun Modifier.noRippleClickable(
    onClick: () -> Unit,
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    indication: Indication? = null,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
) = composed {
    clickable(
        interactionSource = remember { interactionSource },
        indication = indication,
        enabled = enabled,
        onClickLabel = onClickLabel,
        onClick = onClick,
        role = role
    )
}
