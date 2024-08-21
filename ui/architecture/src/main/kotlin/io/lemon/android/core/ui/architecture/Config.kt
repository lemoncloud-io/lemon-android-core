package io.lemon.android.core.ui.architecture

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel.Factory.RENDEZVOUS
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharedFlow

/**
 * [Config]
 * 아키텍처 구성 정보
 * @see [Channel]
 * @see [SharedFlow]
 */
data class Config<EVENT>(
    val errorReplay: Int = 0,
    val errorExtraBufferCapacity: Int = 1,
    val errorOnBufferOverflow: BufferOverflow = BufferOverflow.DROP_OLDEST,
    val effectReplay: Int = 0,
    val effectExtraBufferCapacity: Int = 1,
    val effectOnBufferOverflow: BufferOverflow = BufferOverflow.DROP_OLDEST,
    val eventCapacity: Int = RENDEZVOUS,
    val eventOnBufferOverflow: BufferOverflow = BufferOverflow.DROP_OLDEST,
    val eventOnUndeliveredElement: ((EVENT) -> Unit)? = null
)