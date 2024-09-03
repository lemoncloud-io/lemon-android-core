package io.lemon.core.architecture.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch

/**
 * [BaseViewModel]
 *
 * UI 내 state, event, effect를 처리하기 위한 MVI 구조의 뷰 모델 아키텍처
 *
 *
 * @author raine@lemoncloud.io
 */
abstract class BaseViewModel<STATE : BaseState, EVENT : BaseEvent, EFFECT : BaseEffect, ERROR : BaseError>() :
    ViewModel() {

    /**
     * [initialState]
     *
     * UI에 사용될 상태의 초기 값
     *
     */
    private val initialState by lazy { createInitialState() }

    /**
     * [config]
     *
     * 아키텍처의 기본 구성 정보
     *
     * error, event, effect 플로우에 관한 구성 정보
     *
     * @see Config
     */
    private val config by lazy { createConfig() }

    protected abstract fun createInitialState(): STATE
    protected abstract fun createConfig(): Config<EVENT>

    /**
     * [state]
     *
     * UI 상태 플로우
     *
     * 해당 값을 관측하여 UI를 업데이트 할 수 있습니다.
     */
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<STATE> = _state.asStateFlow()

    /**
     * [error]
     *
     * 오류 플로우
     *
     * 오류가 생길 경우 해당 플로우에 실어 오류를 전달할 수 있습니다.
     */
    private val _error: MutableSharedFlow<ERROR> = MutableSharedFlow(
        replay = config.errorReplay,
        extraBufferCapacity = config.errorExtraBufferCapacity,
        onBufferOverflow = config.errorOnBufferOverflow
    )
    val error: SharedFlow<ERROR> = _error.asSharedFlow()

    /**
     * [event]
     *
     * 이벤트를 해당 플로우에 실어 전달할 수 있습니다.
     *
     */
    private val _event: Channel<EVENT> = Channel(
        capacity = config.eventCapacity,
        onBufferOverflow = config.eventOnBufferOverflow,
        onUndeliveredElement = config.eventOnUndeliveredElement
    )
    val event: Flow<EVENT> = _event.receiveAsFlow()

    /**
     * [effect]
     *
     * 사이드 이펙트 플로우
     *
     */
    private val _effect = MutableSharedFlow<EFFECT>(
        replay = config.effectReplay,
        extraBufferCapacity = config.effectExtraBufferCapacity,
        onBufferOverflow = config.effectOnBufferOverflow
    )
    val effect: SharedFlow<EFFECT> = _effect.asSharedFlow()

    /**
     * [updateState]
     *
     * 상태 업데이트
     *
     * @see [BaseViewModel.updateAndGetState]
     * @see [BaseViewModel.getAndUpdateState]
     */
    protected fun updateState(action: STATE.() -> STATE) = _state.update(action)

    protected fun updateAndGetState(action: STATE.() -> STATE): STATE = _state.updateAndGet(action)

    protected fun getAndUpdateState(action: STATE.() -> STATE): STATE = _state.getAndUpdate(action)

    /**
     * [tryEmitError]
     *
     * error 플로우 내 새로운 error 전달
     *
     * tryEmit 을 사용하기 때문에 에러가 손실될 수 있습니다. (Config의 설정 값을 수정하여 제어할 수 있습니다.)
     *
     * @see Config
     */
    protected val tryEmitError: (ERROR) -> Unit =
        { e -> viewModelScope.launch { _error.tryEmit(e) } }

    val onEvent: (EVENT) -> Unit =
        { e -> viewModelScope.launch { _event.send(e) } }

    /**
     * [tryEmitEffect]
     *
     * 이펙트 플로우 내 새로운 이펙트 전달
     *
     * tryEmit 을 사용하기 때문에 이펙트가 손실될 수 있습니다. (Config의 설정 값을 수정하여 제어할 수 있습니다.)
     *
     * @see Config
     */
    protected val tryEmitEffect: (EFFECT) -> Unit =
        { e -> _effect.tryEmit(e) }


    /**
     * [bindError]
     *
     * 에러 플로우를 관측합니다.
     *
     * Error 에 대한 동작을 구현하여 적용합니다.
     *
     */
    fun bindError(scope: CoroutineScope = viewModelScope, action: suspend (ERROR) -> Unit) =
        error.onEach { action(it) }.launchIn(scope)

    /**
     * [bindEvent]
     *
     * 이벤트 플로우를 관측합니다
     *
     * Event 에 대한 동작을 구현하여 적용합니다.
     */
    fun bindEvent(scope: CoroutineScope = viewModelScope, action: suspend (EVENT) -> Unit) =
        event.onEach { action(it) }.launchIn(scope)

    /**
     * [bindEvent]
     *
     * 이펙트 플로우를 관측합니다
     *
     * Effect 에 대한 동작을 구현하여 적용합니다.
     */
    suspend fun bindEffect(scope: CoroutineScope, action: suspend (EFFECT) -> Unit) =
        effect.onEach { action(it) }.launchIn(scope)

}


