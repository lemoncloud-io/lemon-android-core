package io.lemon.android.core.ui

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel.Factory.RENDEZVOUS
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharedFlow

/**
 * [Config]
 * Configuration of architecture.
 * @see [Channel]
 * @see [SharedFlow]
 */
data class Config<EVENT>(
    val errorReplay: Int = 0,
    val errorExtraBufferCapacity: Int = 0,
    val errorOnBufferOverflow: BufferOverflow = BufferOverflow.SUSPEND,
    val effectReplay: Int = 0,
    val effectExtraBufferCapacity: Int = 0,
    val effectOnBufferOverflow: BufferOverflow = BufferOverflow.SUSPEND,
    val eventCapacity: Int = RENDEZVOUS,
    val eventOnBufferOverflow: BufferOverflow = BufferOverflow.SUSPEND,
    val eventOnUndeliveredElement: ((EVENT) -> Unit)? = null
)