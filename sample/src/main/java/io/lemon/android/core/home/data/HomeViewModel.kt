package io.lemon.android.core.home.data

import io.lemon.android.core.ui.architecture.Config
import io.lemon.android.core.ui.architecture.BaseViewModel

class HomeViewModel : BaseViewModel<HomeState, HomeEvent, HomeEffect, HomeError>() {


    override fun createInitialState(): HomeState = HomeState()
    override fun createConfig(): Config<HomeEvent> = Config()


    init {
        bindEvent {
            when (it) {
                HomeEvent.OnClickCountDownButton -> {
                    if (state.value.count <= 0) tryEmitError(HomeError(message = "0 이하로 내릴 수 없습니다.", exception =  null))
                    else updateState { copy(count = count - 1) }
                }

                HomeEvent.OnClickCountUpButton -> updateState { copy(count = count + 1) }
            }
        }

        bindError { tryEmitEffect(HomeEffect.ShowSnackbar(it.message)) }
    }

}