package io.lemoncloud.core.architecture.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

/**
 * [FlowUseCase]
 * - 도메인 레이어의 유즈 케이스를 처리합니다.
 * - [Flow] 형태로 반환됩니다.
 * - [execute] 를 구현하여 `UseCase`를 생성할 수 있습니다.
 */
abstract class FlowUseCase<In, Out>(private val coroutineDispatcher: CoroutineDispatcher) {

    protected abstract suspend fun execute(params: In): Flow<Out>

    suspend operator fun invoke(parameters: In): Flow<Out> {
        return execute(parameters).flowOn(coroutineDispatcher)
    }
}
