package io.lemon.android.ui.home.data

import io.lemon.android.core.ui.BaseError
import io.lemon.android.core.ui.BaseViewModel
import io.lemon.android.core.ui.Config
import kotlinx.coroutines.delay

class HomeViewModel : BaseViewModel<HomeState, HomeEvent, HomeEffect, HomeError>() {


    override fun createInitialState(): HomeState = HomeState()
    override fun createConfig(): Config<HomeEvent> = Config()


    init {
        bindEvent {
            when (it) {
                HomeEvent.OnClickNavigateButton -> {
                    updateState { copy(isLoading = true) }
                    delay(1000L)
                    updateState { copy(isLoading = false) }
                    tryEmitEffect(HomeEffect.NavigateDetail)
                }

                HomeEvent.OnClickCountDownButton -> {
                    if (state.value.count <= 0) {
                        val error =HomeError(message = "error", exception =  null)
                        error.printErrorLog("에러!!")
                        tryEmitError(error)
                    }
                    else updateState { copy(count = count - 1) }
                }

                HomeEvent.OnClickCountUpButton -> updateState { copy(count = count + 1) }
            }
        }

        bindError {
            HomeEffect.ShowSnackbar(it.message)
        }
    }

}