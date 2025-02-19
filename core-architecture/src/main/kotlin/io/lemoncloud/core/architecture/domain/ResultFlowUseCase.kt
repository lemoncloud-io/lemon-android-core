package io.lemoncloud.core.architecture.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

/**
 * [ResultFlowUseCase]
 * - 도메인 레이어의 유즈 케이스를 처리합니다.
 * - [Result] 타입의 [Flow] 형태로 반환됩니다.
 * - [execute] 를 구현하여 `UseCase`를 생성할 수 있습니다.
 */
abstract class ResultFlowUseCase<In, Out>(private val coroutineDispatcher: CoroutineDispatcher) {

    protected abstract suspend fun execute(params: In): Flow<Result<Out>>

    suspend operator fun invoke(parameters: In): Flow<Result<Out>> {
        return execute(parameters).flowOn(coroutineDispatcher)
    }
}
