package io.lemon.android.core.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch

abstract class BaseViewModel<STATE : BaseState, EVENT : BaseEvent, EFFECT : BaseEffect, ERROR : BaseError>() :
    ViewModel() {

    /**
     * [initialState]
     *
     * Initial state to be used for UI
     */
    private val initialState by lazy { createInitialState() }

    /**
     * [config]
     *
     * Configuration of architecture; You can change settings for error, event and effect flow
     *
     * @see Config
     */
    private val config by lazy { createConfig() }

    protected abstract fun createInitialState(): STATE
    protected abstract fun createConfig(): Config<EVENT>

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<STATE> = _state.asStateFlow()
    val stateValue: STATE = state.value

    private val _error: MutableSharedFlow<ERROR> = MutableSharedFlow(
        replay = config.errorReplay,
        extraBufferCapacity = config.errorExtraBufferCapacity,
        onBufferOverflow = config.errorOnBufferOverflow
    )
    val error: SharedFlow<ERROR> = _error.asSharedFlow()

    private val _event: Channel<EVENT> = Channel(
        capacity = config.eventCapacity,
        onBufferOverflow = config.eventOnBufferOverflow,
        onUndeliveredElement = config.eventOnUndeliveredElement
    )
    val event: Flow<EVENT> = _event.receiveAsFlow()

    private val _effect = MutableSharedFlow<EFFECT>(
        replay = config.effectReplay,
        extraBufferCapacity = config.effectExtraBufferCapacity,
        onBufferOverflow = config.effectOnBufferOverflow
    )
    val effect: SharedFlow<EFFECT> = _effect.asSharedFlow()

    protected fun updateState(action: STATE.() -> STATE) = _state.update(action)

    protected fun updateAndGetState(action: STATE.() -> STATE): STATE = _state.updateAndGet(action)

    protected fun getAndUpdateState(action: STATE.() -> STATE): STATE = _state.getAndUpdate(action)

    protected val tryEmitError: (ERROR) -> Unit =
        { e -> viewModelScope.launch { _error.tryEmit(e) } }

    val onEvent: (EVENT) -> Unit =
        { e -> viewModelScope.launch { _event.send(e) } }

    protected val tryEmitEffect: (EFFECT) -> Unit =
        { e -> _effect.tryEmit(e) }


    /**
     * [bindError]
     * TODO Add comment.
     */
    fun bindError(action: suspend (ERROR) -> Unit) =
        error.onEach { action(it) }.launchIn(viewModelScope)

    /**
     * [bindEvent]
     * TODO Add comment.
     */
    fun bindEvent(action: suspend (EVENT) -> Unit) =
        event.onEach { action(it) }.launchIn(viewModelScope)

    /**
     * [bindEffect]
     * TODO Add comment.
     */
    suspend fun bindEffect(action: suspend (EFFECT) -> Unit) =
        effect.onEach { action(it) }.collect()


}


