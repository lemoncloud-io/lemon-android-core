package io.lemon.core.compose.util.modifier

import androidx.compose.foundation.Indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.selection.toggleable
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
 * Toggle interaction 이 연속적으로 발생하는 것을 방지하는 확장자
 */
fun Modifier.throttleToggleable(
    value: Boolean,
    onValueChange: (Boolean) -> Unit,
    enabled: Boolean = true,
    role: Role? = null,
    coroutineDispatcher: CoroutineDispatcher = Dispatchers.Main,
    throttleTime: Long = 250L,
) = composed {
    val coroutineScope = rememberCoroutineScope { coroutineDispatcher }
    var lastEmissionTime: Long by remember { mutableLongStateOf(0L) }

    toggleable(
        value = value,
        onValueChange = {
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastEmissionTime >= throttleTime) {
                coroutineScope.launch {
                    lastEmissionTime = currentTime
                    onValueChange(it)
                }
            }
            lastEmissionTime = currentTime
        },
        enabled = enabled,
        role = role,
    )
}

/**
 * Toggle interaction 이 연속적으로 발생하는 것을 방지하는 확장자
 */
fun Modifier.throttleToggleable(
    value: Boolean,
    onValueChange: (Boolean) -> Unit,
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    indication: Indication? = null,
    enabled: Boolean = true,
    role: Role? = null,
    coroutineDispatcher: CoroutineDispatcher = Dispatchers.Main,
    throttleTime: Long = 250L,
) = composed {
    val coroutineScope = rememberCoroutineScope { coroutineDispatcher }
    var lastEmissionTime: Long by remember { mutableLongStateOf(0L) }

    noRippleToggleable(
        value = value,
        onValueChange = {
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastEmissionTime >= throttleTime) {
                coroutineScope.launch {
                    lastEmissionTime = currentTime
                    onValueChange(it)
                }
            }
            lastEmissionTime = currentTime
        },
        interactionSource = remember { interactionSource },
        indication = indication,
        enabled = enabled,
        role = role
    )
}

/**
 * Toggle interaction 중 발생하는 시각 이펙트를 제거하는 확장자
 */
fun Modifier.noRippleToggleable(
    value: Boolean,
    onValueChange: (Boolean) -> Unit,
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
    indication: Indication? = null,
    enabled: Boolean = true,
    role: Role? = null,
) = composed {
    toggleable(
        value = value,
        interactionSource = remember { interactionSource },
        indication = indication,
        enabled = enabled,
        onValueChange = onValueChange,
        role = role
    )
}
